package threads.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by gbouriga on 22/10/15.
 */
public class ThreadCreationRunnable implements Runnable {

    private int threadNum;

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadCreationRunnable.class);

    public ThreadCreationRunnable(int threadNum) {
        this.threadNum = threadNum;
    }

    public void run() {
        for (int i = 0; i <= ThreadConstant.NUMBER_OF_CYCLES; i++) {
            LOGGER.info("Thread {} is running in loop {}", this.threadNum, i);
        }
    }
}