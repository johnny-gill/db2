package com.example.db2.service;

import com.example.db2.domain.Item;
import com.example.db2.repository.ItemSearchCond;
import com.example.db2.repository.ItemUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {
    // 저장
    Item save(Item item);

    // 단건 조회
    Optional<Item> findById(Long id);

    // 목록 조회
    List<Item> findAll(ItemSearchCond searchCond);

    // 수정
    void update(Long id, ItemUpdateDto updateItem);
}
