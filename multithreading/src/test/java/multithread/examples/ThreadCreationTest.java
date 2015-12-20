package multithread.examples;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gbouriga on 22/10/15.
 */
public class ThreadCreationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadCreationTest.class);


    private static ThreadCreationThreadPools threadSyncPools;


    @BeforeClass
    public static void init() {
        threadSyncPools = new ThreadCreationThreadPools();
    }

    @Test
    public void testThreadCreationThread() throws InterruptedException {
        //parallels threads execution
        ThreadCreationThread threadCreationThread1 = new ThreadCreationThread(1);
        ThreadCreationThread threadCreationThread2 = new ThreadCreationThread(2);

        //start threads
        threadCreationThread1.start();
        threadCreationThread2.start();

        //prevent test from finishing before the creation thread has complete
        threadCreationThread1.join();
        threadCreationThread2.join();

    }

    @Test
    public void testThreadCreationRunnable() throws InterruptedException {
        //parallels threads execution
        ThreadCreationRunnable threadCreationRunnable1 = new ThreadCreationRunnable(1);
        ThreadCreationRunnable threadCreationRunnable2 = new ThreadCreationRunnable(2);

        Thread thread1 = new Thread(threadCreationRunnable1);
        Thread thread2 = new Thread(threadCreationRunnable2);


        //start threads
        thread1.start();
        thread2.start();

        //prevent test from finishing before the creation thread has complete
        thread1.join();
        thread2.join();

    }

    @Test
    public void testThreadCreationSimple() throws InterruptedException {
        //parallels threads execution
        ThreadCreationSimple threadCreationSimple = new ThreadCreationSimple();

        threadCreationSimple.threadsRun();
    }

    @Test
    public void testThreadSynchronizationVolatile() throws InterruptedException {
        //parallels threads execution
        ThreadSynchronizationVolatile threadSynchronizationVolatile1 = new ThreadSynchronizationVolatile(1);
        ThreadSynchronizationVolatile threadSynchronizationVolatile2 = new ThreadSynchronizationVolatile(2);

        //start threads
        threadSynchronizationVolatile1.start();
        threadSynchronizationVolatile2.start();

        threadSynchronizationVolatile1.setShutdownThread(true);//shutting down the first thread hasn't any impact in thread 2
        threadSynchronizationVolatile2.join(500);

    }

    @Test
    public void testThreadSynchronizationThreadPools() throws InterruptedException {

        //start threads
        threadSyncPools.processingThreadPools();
        LOGGER.info("Thread pools = {} , counter = {}", ThreadConstant.NUMBER_OF_THREAD_POOLS, threadSyncPools.getCounter());
        int expectedCounter = ThreadConstant.NUMBER_OF_CYCLES * 2;
        Assert.assertEquals(expectedCounter, threadSyncPools.getCounter().intValue());

    }
}