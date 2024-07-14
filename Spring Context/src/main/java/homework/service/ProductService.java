package homework.service;

import homework.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    void addProduct(Product product);

    List<Product> getAllProducts();

    Optional<Product> getProductById(int id);
}
