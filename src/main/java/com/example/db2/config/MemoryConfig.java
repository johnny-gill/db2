package com.example.db2.config;

import com.example.db2.repository.ItemRepository;
import com.example.db2.repository.memory.MemoryItemRepository;
import com.example.db2.service.ItemService;
import com.example.db2.service.ItemServiceV1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemoryConfig {

    @Bean
    public ItemRepository itemRepository() {
        return new MemoryItemRepository();
    }

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }

}
