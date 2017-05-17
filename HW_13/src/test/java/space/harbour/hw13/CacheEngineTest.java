package space.harbour.hw13;

import org.junit.Assert;
import org.junit.Test;
import space.harbour.hw13.cache.CacheEngine;
import space.harbour.hw13.cache.CacheEngineImpl;
import space.harbour.hw13.cache.MyElement;

public class CacheEngineTest {
    @Test
    public void simpleTest() throws InterruptedException {
        int size = 5;
        CacheEngine<Integer, String> cache = new CacheEngineImpl<>(size, 1000, 0, false);
        Thread firstThread = new Thread(() -> {
            System.out.println("Started to initialize first thread... ");
            cache.acquireLock();
            cache.put(new MyElement<>(0, "First wrote: " + 0));
            System.out.println("First wrote: " + 0);
            cache.releaseLock();

            cache.acquireLock();
            cache.put(new MyElement<>(1, "First wrote: " + 1));
            System.out.println("First wrote: " + 1);
            cache.releaseLock();
            System.out.println("End of first thread!");
        });
        firstThread.setName("First Thread");

        Thread secondThread = new Thread(() -> {
            System.out.println("Started to initialize second thread... ");
            cache.acquireLock();
            cache.put(new MyElement<>(10, "Second wrote: " + 10));
            System.out.println("Second wrote: " + 10);
            cache.releaseLock();

            cache.acquireLock();
            cache.put(new MyElement<>(20, "Second wrote: " + 20));
            System.out.println("Second wrote: " + 20);
            cache.releaseLock();
            System.out.println("End of second thread!");
        });
        secondThread.setName("Second Thread");

        firstThread.start();
        secondThread.start();

        firstThread.join();
        secondThread.join();
    }
}