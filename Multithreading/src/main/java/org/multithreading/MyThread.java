package org.multithreading;

public class MyThread extends java.lang.Thread {
    private final ThreadPool threadPool;

    public MyThread(ThreadPool threadPool) {
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                Runnable task = threadPool.taskQueue.take();
                task.run();
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}
