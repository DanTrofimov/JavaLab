package ThreadPool;

import lombok.SneakyThrows;

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
               /* if(threads[i].getState().toString().equals("WAITING")) {
                    System.out.println("YES");
                    threads[i].notify();
                    break;
                }*/
            }
        }
    }

    // класс - рабочий поток
    private class PoolWorker extends Thread {
        @SneakyThrows
        @Override
        public void run() {
            while (true) {

                Runnable task = tasks.poll();
                if (task != null) {
                    task.run();

                }
                synchronized (this) {
                    if (tasks.size() == 0) {
                        wait();
                    }

                }
            }
        }
    }
}

