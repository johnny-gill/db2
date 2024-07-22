package com.example.db2.config;

import com.example.db2.repository.ItemRepository;
import com.example.db2.repository.jdbctemplate.JdbcTemplateItemRepositoryV2;
import com.example.db2.repository.jdbctemplate.JdbcTemplateItemRepositoryV3;
import com.example.db2.service.ItemService;
import com.example.db2.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateV3Config {

    private final DataSource dataSource;

    @Bean
    public ItemRepository itemRepository() {
        return new JdbcTemplateItemRepositoryV3(dataSource);
    }

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }
}
