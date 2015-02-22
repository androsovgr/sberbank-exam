package ru.sberbank.exam.mine.cache;

import org.apache.commons.collections.map.LRUMap;

public class LRUMapCache implements Cache {
	private LRUMap cache;

	public LRUMapCache(int cacheSize) {
		cache = new LRUMap(cacheSize);
	}

	@Override
	public byte[] get(byte[] input) {
		return (byte[]) cache.get(new String(input));
	}

	@Override
	public void put(byte[] key, byte[] value) {
		cache.put(new String(key), value);
	}
}
