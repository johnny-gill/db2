package com.example.db2.repository;

import com.example.db2.domain.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    // 저장
    Item save(Item item);

    // 단건 조회
    Optional<Item> findById(Long id);

    // 목록 조회
    List<Item> findAll(ItemSearchCond searchCond);

    // 수정
    void update(Long id, ItemUpdateDto updateItem);
}
