package homework;

import homework.service.CartService;
import homework.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

@ComponentScan
public class Application {
    private final static Logger logger = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        context.getBean(Initializer.class).initializeRepository();

        showMenu();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CartService cartService = getCart(context);

        try {
            while (true) {
                logger.info("Выберите действие:");
                int choice = Integer.parseInt(reader.readLine());
                if (choice == 0) {
                    break;
                } else if (choice == 1) {
                    context.getBean(ProductService.class).getAllProducts().
                            forEach(item -> logger.info(item.toString()));
                } else if (choice == 2) {
                    logger.info(context.getBean(ProductService.class).
                            getProductById(readProductId(reader)).toString());
                } else if (choice == 3) {
                    cartService.addToCart(readProductId(reader));
                } else if (choice == 4) {
                    cartService.deleteFromCart(readProductId(reader));
                } else if (choice == 5) {
                    cartService = getCart(context);
                } else {
                    logger.warning("Такого действия не существует");
                }
                showMenu();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        logger.info(
                "0 - Выход;\n" +
                        "1 - Показать каталог товаров\n" +
                        "2 - Показать информацию о товаре\n" +
                        "3 - Добавить товар в корзину\n" +
                        "4 - Удалить товар из корзины\n" +
                        "5 - Очистить корзину");
    }

    private static int readProductId(BufferedReader reader) throws IOException {
        logger.info("Введите id товара:");
        return Integer.parseInt(reader.readLine());
    }

    private static CartService getCart(ApplicationContext context) {
        return context.getBean(CartService.class);
    }
}
