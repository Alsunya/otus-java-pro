package org.multithreading;

public class MyThread extends java.lang.Thread {
    private final ThreadPool threadPool;

    public MyThread(ThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            Runnable task;
            synchronized (threadPool) {
                // Ждем появления задачи, если очередь пуста
                while (threadPool.taskQueue.isEmpty() && !threadPool.isShutdown) {
                    try {
                        threadPool.wait();
                    } catch (InterruptedException e) {
                        if (threadPool.isShutdown) {
                            return;
                        }
                    }
                }
                // Выходим, если пул завершен и очередь пуста
                if (threadPool.isShutdown && threadPool.taskQueue.isEmpty()) {
                    return;
                }
                // Получаем задачу из очереди
                task = threadPool.taskQueue.poll();
            }
            // Выполняем задачу
            try {
                assert task != null;
                task.run();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
    }
}
