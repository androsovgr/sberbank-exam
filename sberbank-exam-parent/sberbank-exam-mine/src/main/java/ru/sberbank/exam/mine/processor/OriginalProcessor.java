package ru.sberbank.exam.mine.processor;

import ru.sberbank.exam.mine.cache.Cache;
import ru.sberbank.exam.mine.cache.OriginalCache;
import ru.sberbank.exam.mine.process.Process;

public class OriginalProcessor implements Processor {

	private Process process;
	private Cache cache = new OriginalCache();

	public OriginalProcessor(Process process) {
		super();
		this.process = process;
	}

	@Override
	public byte[] process(byte[] input) {
		byte[] result = cache.get(input);
		if (result == null) {
			synchronized (cache) {
				result = cache.get(input);
				if (result == null) {
					result = process.process(input);
					cache.put(input, result);
				}
			}
		}

		return result;
	}

	@Override
	public Process getProcess() {
		return process;
	}

}
