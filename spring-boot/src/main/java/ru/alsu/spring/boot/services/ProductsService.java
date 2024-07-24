package ru.alsu.spring.boot.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.alsu.spring.boot.dtos.CreateProductDto;
import ru.alsu.spring.boot.entities.Product;
import ru.alsu.spring.boot.repositories.ProductsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductsService {
    private final ProductsRepository productsRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductsService.class.getName());

    public List<Product> getAllProducts() {
        logger.info("Произошел вызов getAllProducts");
        return productsRepository.getAllProducts();
    }

    public Optional<Product> getProductById(String id) {
        logger.info("Произошел вызов getProductById");
        return productsRepository.getProductById(id);
    }

    public Product createProduct(CreateProductDto createProductDto) {
        if (createProductDto.getTitle() == null) {
            throw new RuntimeException("Товар не может иметь заголовок null");
        }
        Product product = new Product(createProductDto.getTitle(), createProductDto.getPrice());
        product = productsRepository.createProduct(product);
        logger.info("Создан товар '{}' с id = {}", createProductDto.getTitle(), product.getId());
        return product;
    }
}
