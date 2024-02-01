package edu.hw8.task2;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class FixedThreadPool implements ThreadPool {
    private final BlockingQueue<Runnable> tasks = new LinkedBlockingQueue<>();
    private final TaskExecutor[] taskExecutors;
    private final AtomicBoolean isWorking = new AtomicBoolean(false);


    private FixedThreadPool(int threadsCount) {
        taskExecutors = new TaskExecutor[threadsCount];
    }


    public static FixedThreadPool create(int threadsCount) {
        if (threadsCount < 1) {
            throw new IllegalArgumentException("Number of threads should be more than 0");
        }
        return new FixedThreadPool(threadsCount);
    }

    @Override
    public void start() {
        isWorking.set(true);
        for (int i = 0; i < taskExecutors.length; i++) {
            taskExecutors[i] = new TaskExecutor();
            taskExecutors[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) {
        if (isWorking.get()) {
            try {
                tasks.put(runnable);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RejectedExecutionException("Cannot execute after close or before start methods call");
        }
    }

    @Override
    public void close() {
        isWorking.set(false);
    }

    private final class TaskExecutor extends Thread {
        @Override
        public void run() {
            Runnable task = tasks.poll();
            while (!tasks.isEmpty() || isWorking.get()) {
                if (task == null) {
                    task = tasks.poll();
                    continue;
                }
                task.run();
                task = tasks.poll();
            }
        }
    }
}
