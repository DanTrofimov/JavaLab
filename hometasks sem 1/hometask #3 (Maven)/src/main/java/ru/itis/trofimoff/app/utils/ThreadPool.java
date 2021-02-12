package ru.itis.trofimoff.app.utils;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

// wait, notify, synchronized
public class ThreadPool {
    // очередь задач
    private Deque<Runnable> tasks;

    // пул потоков
    private PoolWorker threads[];

    public ThreadPool(int threadsCount) {
        this.tasks = new ConcurrentLinkedDeque<>();
        this.threads = new PoolWorker[threadsCount];

        for (int i = 0; i < this.threads.length; i++) {
            this.threads[i] = new PoolWorker();
            this.threads[i].start();
        }
    }

    public void submit(Runnable task) {
        tasks.add(task);
        for (int i = 0; i < this.threads.length; i++) {
            synchronized (threads[i]) {
                threads[i].notify();
            }
        }
    }

    // класс - рабочий поток
    private class PoolWorker extends Thread {
        @Override
        public void run() {
            while (true) {
                Runnable task = tasks.poll();
                if (task != null) {
                    task.run();
                }
                synchronized (this) {
                    if (tasks.isEmpty()) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
            }
        }
    }

    /*
        useful links:
            1) wait & notify - https://metanit.com/java/tutorial/8.5.php
            2) synchronized - https://metanit.com/java/tutorial/8.3.php
     */
}

