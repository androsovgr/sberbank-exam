package ru.sberbank.exam.mine.cache;

public class HashMapCacheTest extends CacheTest {

	@Override
	protected Cache setSut() {
		return new HashMapCache();
	}
}
