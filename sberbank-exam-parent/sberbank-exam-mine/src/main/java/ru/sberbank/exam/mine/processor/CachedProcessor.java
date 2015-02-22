package ru.sberbank.exam.mine.processor;

import ru.sberbank.exam.mine.cache.Cache;
import ru.sberbank.exam.mine.process.Process;

public class CachedProcessor implements Processor {

	private Process process;
	private Cache cache;

	public CachedProcessor(Process process, Cache cache) {
		super();
		if (process == null || cache == null) {
			throw new IllegalArgumentException("Arguments can't be null");
		}
		this.process = process;
		this.cache = cache;
	}

	@Override
	public byte[] process(byte[] input) {
		byte[] result = cache.get(input);
		if (result == null) {
			result = process.process(input);
			cache.put(input, result);
		}
		return result;
	}

	@Override
	public Process getProcess() {
		return process;
	}

}
