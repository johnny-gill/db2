package com.example.db2.repository.mybatis;

import com.example.db2.domain.Item;
import com.example.db2.repository.ItemSearchCond;
import com.example.db2.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {
    // 저장
    void save(Item item);

    // 단건 조회
    Optional<Item> findById(Long id);

    // 목록 조회
    List<Item> findAll(ItemSearchCond searchCond);

    // 수정
    void update(@Param("id") Long id, @Param("updateParam") ItemUpdateDto updateItem);
}
