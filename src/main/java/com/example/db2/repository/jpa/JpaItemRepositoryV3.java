package com.example.db2.repository.jpa;

import com.example.db2.domain.Item;
import com.example.db2.domain.QItem;
import com.example.db2.repository.ItemRepository;
import com.example.db2.repository.ItemSearchCond;
import com.example.db2.repository.ItemUpdateDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.example.db2.domain.QItem.item;

@Repository
@Transactional
public class JpaItemRepositoryV3 implements ItemRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;

    public JpaItemRepositoryV3(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public Optional<Item> findById(Long id) {
        Item item = em.find(Item.class, id);
        return Optional.ofNullable(item);
    }

    @Override
    public void update(Long id, ItemUpdateDto updateItem) {
        Item item = findById(id).orElseThrow();
        item.setItemName(updateItem.getItemName());
        item.setQuantity(updateItem.getQuantity());
        item.setPrice(updateItem.getPrice());
    }

    // 리팩토링 전
    public List<Item> findAllOld(ItemSearchCond searchCond) {
        String itemName = searchCond.getItemName();
        Integer maxPrice = searchCond.getMaxPrice();

        BooleanBuilder builder = new BooleanBuilder();

        if (StringUtils.hasText(itemName)) {
            builder.and(item.itemName.like(itemName));
        }
        if (maxPrice != null) {
            builder.and(item.price.loe(maxPrice));
        }

        return query
                .select(item)
                .from(item)
                .where(builder)
                .fetch();
    }

    // 리팩토링 후
    @Override
    public List<Item> findAll(ItemSearchCond searchCond) {

        String itemName = searchCond.getItemName();
        Integer maxPrice = searchCond.getMaxPrice();

        return query
                .select(item)
                .from(item)
                .where(likeItemName(itemName), maxPrice(maxPrice))
                .fetch();
    }

    private BooleanExpression maxPrice(Integer maxPrice) {
        if (maxPrice != null) {
            return item.price.loe(maxPrice);
        }
        return null;
    }

    private BooleanExpression likeItemName(String itemName) {
        if (StringUtils.hasText(itemName)) {
            return item.itemName.like("%" + itemName + "%");
        }
        return null;
    }
}
