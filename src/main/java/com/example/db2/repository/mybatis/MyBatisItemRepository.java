package com.example.db2.repository.mybatis;

import com.example.db2.domain.Item;
import com.example.db2.repository.ItemRepository;
import com.example.db2.repository.ItemSearchCond;
import com.example.db2.repository.ItemUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository implements ItemRepository {

    private final ItemMapper itemMapper;

    @Override
    public Item save(Item item) {
        log.info("itemMapper.getClass()={}", itemMapper.getClass());
        itemMapper.save(item);
        return item;
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemMapper.findById(id);
    }

    @Override
    public List<Item> findAll(ItemSearchCond searchCond) {
        return itemMapper.findAll(searchCond);
    }

    @Override
    public void update(Long id, ItemUpdateDto updateItem) {
        itemMapper.update(id, updateItem);
    }
}
