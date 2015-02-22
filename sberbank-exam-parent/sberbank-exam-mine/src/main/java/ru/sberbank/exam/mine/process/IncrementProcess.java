package ru.sberbank.exam.mine.process;

import java.util.Arrays;

public class IncrementProcess implements Process {

	@Override
	public byte[] process(byte[] input) {
		byte[] result = Arrays.copyOf(input, input.length);
		for (int i = 0; i < result.length; i++) {
			result[i]++;
		}

		return result;
	}
}
