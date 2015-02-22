package ru.sberbank.exam.mine.process;

import java.util.Iterator;

import org.apache.commons.lang.time.StopWatch;
import org.testng.Assert;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.sberbank.exam.mine.util.DataFactory;

public class WaitProcessTest {

	private WaitProcess sut;
	private StopWatch timer;

	@DataProvider
	private static Object[][] getInput() {
		return new Object[][] { { DataFactory.generate(0) }, { DataFactory.generate(1) }, { DataFactory.generate(20) } };
	}

	private static Object[][] getWaitTime() {
		return new Object[][] { { 0 }, { 100 } };
	}

	@DataProvider
	private static Iterator<Object[]> getInputWait() {
		return new Iterator<Object[]>() {

			private int inputCounter;
			private int waitCounter;

			private Object[][] inputs = getInput();
			private Object[][] waits = getWaitTime();

			@Override
			public boolean hasNext() {
				return waitCounter < waits.length;
			}

			@Override
			public Object[] next() {
				Object[] retDate = new Object[] { inputs[inputCounter++][0], waits[waitCounter][0] };
				if (inputCounter >= inputs.length) {
					waitCounter++;
					inputCounter = 0;
				}
				return retDate;
			}
		};
	}

	@BeforeGroups("result")
	public void beforeProcessResult() {
		sut = new WaitProcess(0);
	}

	@BeforeGroups("wait")
	public void beforeProcessWait() {
		timer = new StopWatch();
	}

	@Test(dataProvider = "getInput", groups = { "result" })
	public void processResult(byte[] input) {
		byte[] output = sut.process(input);
		Assert.assertEquals(input, output);
	}

	@Test(dataProvider = "getInputWait", groups = "wait")
	public void processWait(byte[] input, long wait) {
		WaitProcess sut = new WaitProcess(wait);
		timer.start();
		sut.process(input);
		timer.split();
		long delta = timer.getSplitTime();
		timer.reset();

		Assert.assertTrue(delta >= wait);
	}
}
