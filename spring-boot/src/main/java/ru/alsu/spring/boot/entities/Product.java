package ru.alsu.spring.boot.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {
    private String id;
    private String title;
    private int price;

    public Product(String title, int price) {
        this.title = title;
        this.price = price;
    }
}
