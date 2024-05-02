package org.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    final BlockingQueue<Runnable> taskQueue;
    private final MyThread[] threads;
    volatile boolean isShutdown;

    public ThreadPool(int capacity) {
        this.taskQueue = new LinkedBlockingQueue<>();
        this.threads = new MyThread[capacity];
        this.isShutdown = false;

        // Создаем и запускаем рабочие потоки
        for (int i = 0; i < capacity; i++) {
            threads[i] = new MyThread(this);
            threads[i].start();
        }
    }

    public void execute(Runnable task) {
        if (isShutdown) {
            throw new IllegalStateException("Пул потоков был прерван");
        }
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void shutdown() {
        isShutdown = true;
        for (MyThread thread : threads) {
            thread.interrupt();
        }
    }
}
