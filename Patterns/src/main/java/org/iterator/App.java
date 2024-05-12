package org.iterator;

/**
 * Создайте класс Box с четырмя внутренними листами строк,
 * реализуйте Iterator который последовательно возвращает все строки из листов коробки
 */
public class App {
    public static void main(String[] args) {
        Box box = new Box();
        for (String str : box) {
            System.out.println(str);
        }
    }
}
