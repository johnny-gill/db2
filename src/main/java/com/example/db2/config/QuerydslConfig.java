package com.example.db2.config;

import com.example.db2.repository.ItemRepository;
import com.example.db2.repository.jpa.JpaItemRepositoryV3;
import com.example.db2.service.ItemService;
import com.example.db2.service.ItemServiceV1;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class QuerydslConfig {
    private final EntityManager em;

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepositoryV3(em);
    }

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

}
