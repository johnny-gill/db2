package com.example.db2;

import com.example.db2.config.*;
import com.example.db2.repository.ItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/**
 * scanBasePackages 경로외에 생성자 주입이 안되므로, Bean을 통해 주입을 한다!!!
 * 신기하다!!!!!!!!!!
 */

//@Import(MemoryConfig.class)
//@Import(JdbcTemplateV1Config.class)
//@Import(JdbcTemplateV2Config.class)
//@Import(JdbcTemplateV3Config.class)
@Import(MyBatisConfig.class)
@SpringBootApplication(scanBasePackages = "com.example.db2.web")
public class Db2Application {

    public static void main(String[] args) {
        SpringApplication.run(Db2Application.class, args);
    }

    @Bean
    @Profile("local")
    public TestDataInit testDataInit(ItemRepository itemRepository) {
        return new TestDataInit(itemRepository);
    }
}
