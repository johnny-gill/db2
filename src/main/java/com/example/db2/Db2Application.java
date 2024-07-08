package com.example.db2;

import com.example.db2.config.MemoryConfig;
import com.example.db2.repository.ItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * scanBasePackages 경로외에 생성자 주입이 안되므로, Bean을 통해 주입을 한다!!!
 * 신기하다!!!!!!!!!!
 */

@Import(MemoryConfig.class)
@SpringBootApplication(scanBasePackages = "com.example.db2.web")
public class Db2Application {

    public static void main(String[] args) {
        SpringApplication.run(Db2Application.class, args);
    }

    @Bean
    public TestDataInit testDataInit(ItemRepository itemRepository) {
        return new TestDataInit(itemRepository);
    }
}
