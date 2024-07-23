package com.example.db2.repository;

import com.example.db2.domain.Item;
import com.example.db2.repository.memory.MemoryItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


//@Commit // 롤백말고 commit을 해준다.
@Transactional
@SpringBootTest
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

//    @Autowired
//    PlatformTransactionManager transactionManager;
//
//    TransactionStatus status;
//
//    @BeforeEach
//    void beforeEach() {
//        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
//    }

    @AfterEach
    void afterEach() {
        if (itemRepository instanceof MemoryItemRepository) {
            ((MemoryItemRepository) itemRepository).clearStore();
        }

//        transactionManager.rollback(status);
    }

    @Test
    void save() {
        // given
        Item item = new Item();
        item.setItemName("프로틴바");
        item.setPrice(36500);
        item.setQuantity(20);

        // when
        Item saveItem = itemRepository.save(item);
        Item findItem = itemRepository.findById(item.getId()).orElseThrow();

        // then
        assertThat(saveItem).isEqualTo(findItem);
    }

    @Test
    void findAll() {
        // given
        Item item = new Item();
        item.setItemName("프로틴바");
        item.setPrice(36500);
        item.setQuantity(20);
        itemRepository.save(item);


        // when
        // 30000만원 이하인 프로틴바
        ItemSearchCond itemSearchCondWithAll = new ItemSearchCond("프로틴바", 30000);

        // 40000만원 이하인 제품
        ItemSearchCond itemSearchCondWithPrice = new ItemSearchCond();
        itemSearchCondWithPrice.setMaxPrice(40000);


        // then
        assertThat(itemRepository.findAll(itemSearchCondWithAll).size()).isEqualTo(0);
        assertThat(itemRepository.findAll(itemSearchCondWithPrice).size()).isEqualTo(1);
    }

    @Test
    void update() {
        // given
        Item item = new Item();
        item.setItemName("프로틴바");
        item.setPrice(36500);
        item.setQuantity(20);

        // when
        Item saveItem = itemRepository.save(item);
        ItemUpdateDto updateItem = new ItemUpdateDto("칙촉", 10, 2000);
        itemRepository.update(saveItem.getId(), updateItem);
        Item findItem = itemRepository
                .findById(saveItem.getId())
                .orElseThrow();
        // then
        assertThat(findItem.getItemName()).isEqualTo(updateItem.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateItem.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateItem.getQuantity());
    }
}