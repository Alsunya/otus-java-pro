package org.builder;

/**
 * Реализуйте класс Product (id, title, description, cost, weight, width, length, height) и Builder для него
 */
public class App {
    public static void main(String[] args) {
        Product product = Product.builder()
                .id(1)
                .title("Шоколадка")
                .description("Шоколад молочный")
                .cost(100)
                .weight(0.2)
                .width(10)
                .length(60)
                .height(1)
                .build();
    }
}
