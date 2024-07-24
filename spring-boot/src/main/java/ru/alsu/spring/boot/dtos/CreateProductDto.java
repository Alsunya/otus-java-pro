package ru.alsu.spring.boot.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CreateProductDto {
    private String title;
    private int price;
}
