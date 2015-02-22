package ru.sberbank.exam.mine.cache;

import java.util.HashMap;

public class HashMapCache implements Cache {

	private HashMap<String, byte[]> cache = new HashMap<String, byte[]>();

	@Override
	public byte[] get(byte[] input) {
		return cache.get(new String(input));
	}

	@Override
	public void put(byte[] key, byte[] value) {
		cache.put(new String(key), value);

	}

}
