package com.example.db2.repository.v2;

import com.example.db2.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

// spring data jpa. 기본 crud는 이거로 사용
public interface ItemRepositoryV2 extends JpaRepository<Item, Long> {
}
