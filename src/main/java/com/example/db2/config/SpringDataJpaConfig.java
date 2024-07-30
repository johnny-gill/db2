package com.example.db2.config;

import com.example.db2.repository.ItemRepository;
import com.example.db2.repository.jpa.JpaItemRepositoryV2;
import com.example.db2.repository.jpa.SpringDataJpaItemRepository;
import com.example.db2.service.ItemService;
import com.example.db2.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringDataJpaConfig {

    private final SpringDataJpaItemRepository repository;

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepositoryV2(repository);
    }

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

}
