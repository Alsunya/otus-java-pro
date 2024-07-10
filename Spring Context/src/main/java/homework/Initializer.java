package homework;

import homework.model.Product;
import homework.service.ProductService;
import org.springframework.stereotype.Component;

@Component
public class Initializer {
    private final ProductService productService;

    public Initializer(ProductService productService) {
        this.productService = productService;
    }

    public void initializeRepository() {
        productService.addProduct(new Product(1, "Молоко", 150));
        productService.addProduct(new Product(2, "Хлеб", 50));
        productService.addProduct(new Product(3, "Чай", 200));
        productService.addProduct(new Product(4, "Яблоко", 30));
        productService.addProduct(new Product(5, "Масло", 200));
        productService.addProduct(new Product(6, "Йогурт", 100));
        productService.addProduct(new Product(7, "Мясо", 400));
        productService.addProduct(new Product(8, "Сок", 150));
        productService.addProduct(new Product(9, "Чипсы", 90));
        productService.addProduct(new Product(10, "Лимон", 30));
    }
}
