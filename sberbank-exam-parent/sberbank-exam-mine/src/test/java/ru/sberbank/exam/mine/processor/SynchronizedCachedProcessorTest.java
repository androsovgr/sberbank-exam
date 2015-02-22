package ru.sberbank.exam.mine.processor;

import java.util.Arrays;

import org.apache.commons.lang.time.StopWatch;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.sberbank.exam.mine.cache.HashMapCache;
import ru.sberbank.exam.mine.process.WaitProcess;

public class SynchronizedCachedProcessorTest extends CachedProcessorTest {

	@Override
	protected Processor setSut() {
		return new SynchronizedCachedProcessor(new WaitProcess(PROCESS_LENGTH_MS), new HashMapCache());
	}

	@Test(dataProvider = "getInput", timeOut = 10 * PROCESS_LENGTH_MS)
	public void testSynchronized(byte[] input) throws InterruptedException {
		// async starting of operation
		byte[] sameAsInput = Arrays.copyOf(input, input.length);
		Thread asyncCallThreadFirst = new Thread(() -> sut.process(input));
		Thread asyncCallThreadSecond = new Thread(() -> sut.process(sameAsInput));

		asyncCallThreadFirst.start();
		Thread.sleep(PROCESS_LENGTH_MS / 2);
		asyncCallThreadSecond.start();

		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		asyncCallThreadSecond.join();
		stopWatch.split();
		long secondTime = stopWatch.getSplitTime();

		Assert.assertTrue(secondTime < PROCESS_LENGTH_MS, "Second doesn't get from cache. Process time: " + secondTime);

	}
}
