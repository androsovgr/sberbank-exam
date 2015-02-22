package ru.sberbank.exam.mine.processor;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.sberbank.exam.mine.process.Process;
import ru.sberbank.exam.mine.util.DataFactory;

public abstract class ProcessorTest {

	protected Processor sut;

	@DataProvider
	protected static Iterator<Object[]> getInput() {
		return new Iterator<Object[]>() {
			private final int COUNT = 10;
			private int i;

			@Override
			public boolean hasNext() {
				return i < COUNT;
			}

			@Override
			public Object[] next() {
				i++;
				return new Object[] { DataFactory.generate() };
			}
		};
	}

	@DataProvider
	protected static Iterator<Object[]> getInputConst() {
		return new Iterator<Object[]>() {
			private final int COUNT = 100;
			private int i;

			@Override
			public boolean hasNext() {
				return i < COUNT;
			}

			@Override
			public Object[] next() {
				i++;
				return new Object[] { new byte[] { 1, 1, 1, 1, 1 } };
			}
		};
	}

	@BeforeMethod(firstTimeOnly = true)
	public void prepare() {
		sut = setSut();
		if (sut == null) {
			throw new IllegalStateException("Sut can't be null");
		}
	}

	@Test(dataProvider = "getInput")
	public void testResult(byte[] input) {
		Process process = sut.getProcess();
		Assert.assertEquals(process.process(input), sut.process(input));
	}

	protected abstract Processor setSut();
}
