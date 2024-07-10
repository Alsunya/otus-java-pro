package homework.service;

import homework.model.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
@Scope("prototype")
public class CartService {
    private final Logger logger = Logger.getLogger(CartService.class.getName());
    private final List<Product> products;
    private final ProductService productService;

    public CartService(ProductService productService) {
        products = new ArrayList<>();
        this.productService = productService;
        logger.info("Создана новая корзина.\n" +
                "Корзина пуста.");
    }

    public void addToCart(int id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            products.add(product);
            logger.info(String.format("В корзину добавлен товар %s", product.getName()));
        } else {
            notifyAboutAbsence(id);
        }
    }

    public void deleteFromCart(int id) {
        var product = products.stream()
                .filter(item -> item.getId() == id)
                .findFirst().orElse(null);
        if (product != null) {
            products.remove(product);
            logger.info(String.format("Товар %s успешно удален из корзины", product.getName()));
        } else {
            notifyAboutAbsence(id);
        }
    }

    public void notifyAboutAbsence(int id) {
        logger.warning(String.format("Нет товара с id = %d", id));
    }
}
