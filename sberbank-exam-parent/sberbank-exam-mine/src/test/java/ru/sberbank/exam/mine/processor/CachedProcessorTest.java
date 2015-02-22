package ru.sberbank.exam.mine.processor;

import java.util.Arrays;

import org.apache.commons.lang.time.StopWatch;
import org.testng.Assert;
import org.testng.annotations.Test;

import ru.sberbank.exam.mine.cache.HashMapCache;
import ru.sberbank.exam.mine.process.WaitProcess;

public class CachedProcessorTest extends ProcessorTest {

	protected final long PROCESS_LENGTH_MS = 10;

	@Override
	protected Processor setSut() {
		return new CachedProcessor(new WaitProcess(PROCESS_LENGTH_MS), new HashMapCache());
	}

	@Test(dataProvider = "getInput")
	public void testCaching(byte[] input) {
		sut.process(input);
		StopWatch stopWatch = new StopWatch();
		byte[] sameAsInput = Arrays.copyOf(input, input.length);
		stopWatch.start();
		sut.process(sameAsInput);
		stopWatch.split();
		long afterCacheOperationTime = stopWatch.getSplitTime();
		Assert.assertTrue(afterCacheOperationTime < PROCESS_LENGTH_MS);
	}

}
