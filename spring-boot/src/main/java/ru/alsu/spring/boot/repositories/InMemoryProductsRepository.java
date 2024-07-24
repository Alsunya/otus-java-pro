package ru.alsu.spring.boot.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.alsu.spring.boot.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class InMemoryProductsRepository implements ProductsRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
        this.products.add(new Product("1", "Молоко", 150));
        this.products.add(new Product("2", "Хлеб", 50));
        this.products.add(new Product("3", "Чай", 200));
        this.products.add(new Product("4", "Яблоко", 30));
        this.products.add(new Product("5", "Масло", 200));
        this.products.add(new Product("6", "Йогурт", 100));
        this.products.add(new Product("7", "Мясо", 400));
        this.products.add(new Product("8", "Сок", 150));
        this.products.add(new Product("9", "Чипсы", 90));
        this.products.add(new Product("10", "Лимон", 30));
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Optional<Product> getProductById(String id) {
        return products.stream()
                .filter(product -> Objects.equals(product.getId(), id))
                .findFirst();
    }

    @Override
    public Product createProduct(Product product) {
        String newId = String.valueOf(products.stream().mapToInt(p -> Integer.parseInt(p.getId())).max().
                orElse(0) + 1);
        product.setId(newId);
        products.add(product);
        return product;
    }
}
