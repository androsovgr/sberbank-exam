package ru.sberbank.exam.mine.cache;

import java.util.Arrays;
import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.sberbank.exam.mine.util.DataFactory;

public abstract class CacheTest {
	protected Cache sut;

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

	@BeforeMethod()
	public void prepare() {
		sut = setSut();
		if (sut == null) {
			throw new IllegalStateException("Sut can't be null");
		}
	}

	@Test(dataProvider = "getInput")
	public void testResult(byte[] input) {
		sut.put(input, input);
		byte[] sameInputArray = Arrays.copyOf(input, input.length);
		byte[] cached = sut.get(sameInputArray);
		Assert.assertEquals(cached, input);
	}

	protected abstract Cache setSut();

}
