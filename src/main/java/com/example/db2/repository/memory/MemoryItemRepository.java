package com.example.db2.repository.memory;

import com.example.db2.domain.Item;
import com.example.db2.repository.ItemRepository;
import com.example.db2.repository.ItemSearchCond;
import com.example.db2.repository.ItemUpdateDto;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MemoryItemRepository implements ItemRepository {

    private static long sequence = 0L;
    private static final Map<Long, Item> store = new HashMap<>();

    @Override
    public Item save(Item item) {
        item.setId(sequence++);
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Item> findAll(ItemSearchCond searchCond) {
        String itemName = searchCond.getItemName();
        Integer maxPrice = searchCond.getMaxPrice();

        return store.values().stream().filter(item -> {
            if (!StringUtils.hasText(itemName)) { // 제품명이 공란인 경우 모두 검색
                return true;
            }
            return itemName.equals(item.getItemName());
        }).filter(item -> {
            if (maxPrice == null) { // 가격이 공란인 경우 모두 검색
                return true;
            }
            return item.getPrice() <= maxPrice;
        }).collect(Collectors.toList());
    }

    @Override
    public void update(Long id, ItemUpdateDto updateItem) {
        Item item = findById(id).orElseThrow();
        item.setItemName(updateItem.getItemName());
        item.setPrice(updateItem.getPrice());
        item.setQuantity(updateItem.getQuantity());
    }

    public void clearStore() {
        store.clear();
    }
}
