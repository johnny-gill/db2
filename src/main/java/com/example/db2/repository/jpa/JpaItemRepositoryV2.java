package com.example.db2.repository.jpa;

import com.example.db2.domain.Item;
import com.example.db2.repository.ItemRepository;
import com.example.db2.repository.ItemSearchCond;
import com.example.db2.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@RequiredArgsConstructor
public class JpaItemRepositoryV2 implements ItemRepository {

    private final SpringDataJpaItemRepository repository;

    @Override
    public Item save(Item item) {
        return repository.save(item);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Item> findAll(ItemSearchCond searchCond) {
        String itemName = searchCond.getItemName();
        Integer maxPrice = searchCond.getMaxPrice();

        if (StringUtils.hasText(itemName) && maxPrice != null) {
            // return repository.findItems(itemName, maxPrice); // 아래와 동일
            return repository.findByItemNameLikeAndPriceLessThanEqual("%" + itemName + "%", maxPrice);
        } else if (StringUtils.hasText(itemName)) {
            return repository.findByItemNameLike("%" + itemName + "%");
        } else if (maxPrice != null) {
            return repository.findByPriceLessThanEqual(maxPrice);
        } else {
            return repository.findAll();
        }
    }

    @Override
    public void update(Long id, ItemUpdateDto updateItem) {
        Item findItem = repository
                .findById(id)
                .orElseThrow();

        findItem.setItemName(updateItem.getItemName());
        findItem.setQuantity(updateItem.getQuantity());
        findItem.setPrice(updateItem.getPrice());
    }
}
