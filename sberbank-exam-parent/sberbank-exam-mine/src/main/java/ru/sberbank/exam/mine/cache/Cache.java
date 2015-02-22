package ru.sberbank.exam.mine.cache;

public interface Cache {

	public byte[] get(byte[] input);

	public void put(byte[] key, byte[] value);
}
