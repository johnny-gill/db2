package com.example.db2.config;

import com.example.db2.repository.ItemRepository;
import com.example.db2.repository.mybatis.ItemMapper;
import com.example.db2.repository.mybatis.MyBatisItemRepository;
import com.example.db2.service.ItemService;
import com.example.db2.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {
    private final ItemMapper itemMapper;
    // 스프링 연동 모듈(MybatisAutoConfiguration)은 @Mapper가 존재하면 동적 프록시로 구현체를 만들고 Bean으로 등록한다. itemMapper.getClass()=class jdk.proxy3.$Proxy69

    @Bean
    public ItemRepository itemRepository() {
        return new MyBatisItemRepository(itemMapper);
    }

    @Bean
    public ItemService itemService() {
        return new ItemServiceV1(itemRepository());
    }
}
