package homework.service;

import homework.model.Product;

import java.util.List;

public interface ProductService {
    void addProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(int id);
}
