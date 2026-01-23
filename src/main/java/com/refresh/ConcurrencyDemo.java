package com.refresh;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrencyDemo  {
    static int totalAge = 0;
    static List<User> users = null;

    public static void main (String [] args) {
        users = Arrays.asList(
                new User("Peter", 32, "peter@gmail.com"),
                new User("raze", 20, "raze@yahoo.fr"),
                new User("phoenix", 30, "phoenix@gmail.com"),
                new User("Omen", 45, "omen@yahoo.fr"),
                new User("skye", 42, "skye@gmail.com")
        );
        naiveSum();
        System.out.println(totalAge);

        naiveSumExecutor();
        System.out.println(totalAge);

        naiveSumExecutorFuture();
        System.out.println(totalAge);

        naiveSumVirtual();
        System.out.println(totalAge);
    }

    /**
     * Use of Thread to handle concurrency
     */
    public static void naiveSum() {
        List<Thread> threads = new ArrayList<>();
        users.forEach(u -> {
            Thread t = new Thread(() -> addAge(u.age()));
            threads.add(t);
            t.start();
        });

        for (Thread t : threads) {
            try {
                t.join();  // Bloque jusqu'à fin thread
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static synchronized void addAge(int age) {
        System.out.println("Thread " + Thread.currentThread().getName()
                + " ajoute " + age + " → total=" + totalAge);
        totalAge += age;
    }

    /**
     * Use of ExecutorService to handle concurrency
     */
    public static void naiveSumExecutor() {
        totalAge = 0;
        try (ExecutorService executor = Executors.newFixedThreadPool(4)) {
            users.forEach(u -> {
                executor.submit(() -> addAge(u.age()));
            });
        }



    }

    /**
     * Use of ExecutorService with future to handle concurrency
     */
    public static void naiveSumExecutorFuture() {
        // with future
        try (ExecutorService executor1 = Executors.newFixedThreadPool(4)) {
            totalAge = 0;
            users.forEach(u -> {
                executor1.submit(() -> addAge(u.age()));
            });

        }
    }

    public static void naiveSumVirtual() {
        totalAge = 0;
        List<Thread> virtualThreads = new ArrayList<>();
        users.forEach(u -> {
            Thread vt = Thread.ofVirtual().start(() -> addAge(u.age()));
            virtualThreads.add(vt);
        });

        // Attendre TOUS
        for (Thread vt : virtualThreads) {
            try {
                vt.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

}
