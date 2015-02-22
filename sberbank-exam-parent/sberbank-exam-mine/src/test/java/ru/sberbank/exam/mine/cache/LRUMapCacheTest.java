package ru.sberbank.exam.mine.cache;

import java.util.Iterator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ru.sberbank.exam.mine.util.DataFactory;

public class LRUMapCacheTest extends CacheTest {

	private static final int CACHE_SIZE = 10;

	@DataProvider
	protected static Iterator<Object[]> getInputs() {
		return new Iterator<Object[]>() {
			private final int COUNT = 5;
			private final int LIST_LENGTH = CACHE_SIZE + 2;
			private int i;

			@Override
			public boolean hasNext() {
				return i < COUNT;
			}

			@Override
			public Object[] next() {
				i++;
				return new Object[] { DataFactory.generateList(LIST_LENGTH) };
			}
		};
	}

	@Override
	protected Cache setSut() {
		return new LRUMapCache(CACHE_SIZE);
	}

	@Test(dataProvider = "getInputs")
	public void testEvict(byte[][] inputList) {
		for (int i = 0; i < inputList.length; i++) {
			byte[] bs = inputList[i];
			sut.put(bs, bs);
		}

		for (int i = 0; i < inputList.length - CACHE_SIZE; i++) {
			byte[] bs = inputList[i];
			Assert.assertNull(sut.get(bs), "Not Evicted, but must.");
		}
		for (int i = inputList.length - CACHE_SIZE; i < CACHE_SIZE; i++) {
			byte[] bs = inputList[i];
			Assert.assertNotNull(sut.get(bs), "Evicted, but must not.");
		}
	}
}
