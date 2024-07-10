package homework.service;

import homework.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductServiceImpl implements ProductService {
    private final List<Product> products;

    public ProductServiceImpl() {
        products = new ArrayList<>();
    }

    @Override
    public void addProduct(Product product) {
        products.add(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public Product getProductById(int id) {
        return products.stream()
                .filter(item -> item.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
