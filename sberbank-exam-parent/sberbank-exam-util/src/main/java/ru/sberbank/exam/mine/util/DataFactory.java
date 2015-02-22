package ru.sberbank.exam.mine.util;

import java.util.Random;

public class DataFactory {

	private static final Random RANDOM = new Random();

	public static byte[] generate(int length) {
		byte[] bytes = new byte[length];
		RANDOM.nextBytes(bytes);
		return bytes;
	}

	public static byte[] generate() {
		return generate(RANDOM.nextInt(1000));
	}

	public static byte[][] generateList(int listLength) {
		byte[][] generated = new byte[listLength][];
		for (int i = 0; i < generated.length; i++) {
			generated[i] = generate();
		}
		return generated;
	}
}
