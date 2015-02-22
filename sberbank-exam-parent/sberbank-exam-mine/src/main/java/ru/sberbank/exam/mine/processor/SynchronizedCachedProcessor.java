package ru.sberbank.exam.mine.processor;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import ru.sberbank.exam.mine.cache.Cache;
import ru.sberbank.exam.mine.process.Process;

public class SynchronizedCachedProcessor implements Processor {

	private Process process;
	private Cache cache;
	private ConcurrentMap<String, String> locks = new ConcurrentHashMap<String, String>();

	public SynchronizedCachedProcessor(Process process, Cache cache) {
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
			synchronized (getCacheSyncObject(new String(input))) {
				result = cache.get(input);
				if (result == null) {
					result = process.process(input);
					cache.put(input, result);
				}
			}
		}
		return result;
	}

	private Object getCacheSyncObject(final String id) {
		locks.putIfAbsent(id, id);
		return locks.get(id);
	}

	@Override
	public Process getProcess() {
		return process;
	}

}
