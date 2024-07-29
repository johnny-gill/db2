package com.example.db2.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @Column(name = "item_name", length = 10) // item_name을 camelCase로 자동 변경해줘서 안해도 됨
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() { // 기본 생성자 필수
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

}
