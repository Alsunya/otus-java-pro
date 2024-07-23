package ru.alsu.spring.boot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.alsu.spring.boot.dtos.CreateProductDto;
import ru.alsu.spring.boot.entities.Product;
import ru.alsu.spring.boot.services.ProductsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class ProductController {
    private final ProductsService productsService;

    @GetMapping("/{id}")
    public Optional<Product> getProductDetails(@PathVariable String id) {
        return productsService.getProductById(id);
    }

    @GetMapping
    public List<Product> getAllAccounts() {
        return productsService.getAllProducts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createNewAccount(@RequestBody CreateProductDto createProductDto) {
        return productsService.createProduct(createProductDto);
    }
}
