package org.wrj.dolphin.forkjoin;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.Test;


public class ForkJoinTest {
	
	final int SIZE = 100;
	
    @Test
    public void run1() throws Exception{
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> result = forkJoinPool.submit(new Calculator(0, 10000));

        assertEquals(new Integer(49995000), result.get());
    }

	@Test
	public void run2() throws InterruptedException {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		Random rnd = new Random();
		long[] array = new long[SIZE];
		for (int i = 0; i < SIZE; i++) {
			array[i] = rnd.nextInt();
		}
		forkJoinPool.submit(new SortTask(array));

		forkJoinPool.shutdown();
		forkJoinPool.awaitTermination(1000, TimeUnit.SECONDS);

		for (int i = 1; i < SIZE; i++) {
			assertTrue(array[i - 1] < array[i]);
		}
	}

}
