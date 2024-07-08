package com.example.db2;


import com.example.db2.domain.Item;
import com.example.db2.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        log.info("테스트 데이터 저장");
        itemRepository.save(new Item("뉴진스", 10000, 5));
        itemRepository.save(new Item("아일릿", 3000, 6));
    }
}
