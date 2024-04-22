package org.multithreading;

/**
 * ТЗ:
 * Реализуйте собственный пул потоков.
 * Конструктор пула в качестве аргументов ожидает емкость (количество рабочих потоков).
 * После создания объекта пула, он сразу инициализирует и запускает потоки.
 * Внутри пула очередь задач на исполнение организуется через LinkedList.
 * Для передачи пулу новой задачи используется метод execute(Runnable r),
 * указанная задача попадает в очередь исполнения, и как только появится свободный поток –
 * передается на исполнение. Также необходимо реализовать метод shutdown(),
 * после выполнения которого новые задачи больше не принимаются пулом
 * (при попытке добавить задачу можно бросать IllegalStateException),
 * а все потоки для которых больше нет задач завершают свою работу.
 */
public class App {
    public static void main(String[] args) {

        ThreadPool threadPool = new ThreadPool(10);

        for (int i = 0; i < 50; i++) {
            int taskId = i + 1;
            threadPool.execute(() -> {
                System.out.println("Задача " + taskId + " выполняется потоком: " +
                        MyThread.currentThread().getName());
                try {
                    MyThread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Задача " + taskId + " выполнена");
            });
        }

        try {
            MyThread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPool.shutdown();
    }
}
