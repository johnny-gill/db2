package com.example.db2.config;

import com.example.db2.repository.ItemRepository;
import com.example.db2.repository.jpa.JpaItemRepositoryV1;
import com.example.db2.service.ItemService;
import com.example.db2.service.ItemServiceV1;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {

    private final EntityManager em;

    public JpaConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepositoryV1(em);
    }

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }
}
