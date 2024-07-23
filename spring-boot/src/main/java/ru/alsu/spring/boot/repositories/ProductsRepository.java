package ru.alsu.spring.boot.repositories;

import ru.alsu.spring.boot.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductsRepository {
    List<Product> getAllProducts();

    Optional<Product> getProductById(String id);

    Product createProduct(Product product);
}
