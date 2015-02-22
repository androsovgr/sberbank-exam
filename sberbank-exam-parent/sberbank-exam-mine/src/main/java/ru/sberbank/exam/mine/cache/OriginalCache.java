package ru.sberbank.exam.mine.cache;

import java.util.HashMap;

public class OriginalCache implements Cache {

	private HashMap<byte[], byte[]> storage = new HashMap<byte[], byte[]>();

	@Override
	public byte[] get(byte[] input) {
		return storage.get(input);
	}

	@Override
	public void put(byte[] key, byte[] value) {
		storage.put(key, value);
	}
}
