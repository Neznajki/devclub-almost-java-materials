package dev.club.materials.good.imports;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutor {

    private final ExecutorService executor =
        Executors.newFixedThreadPool(2);

    public void runAsync(Runnable task) {

        executor.submit(task);
    }
}
