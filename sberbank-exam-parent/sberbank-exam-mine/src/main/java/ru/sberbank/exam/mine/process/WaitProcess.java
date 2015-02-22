package ru.sberbank.exam.mine.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitProcess implements Process {

	private static final Logger LOGGER = LoggerFactory.getLogger(WaitProcess.class);

	private long waitTime;

	public WaitProcess(long wait) {
		this.waitTime = wait;
	}

	@Override
	public byte[] process(byte[] input) {
		if (waitTime != 0) {
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				LOGGER.error("Can't sleep", e);
				throw new RuntimeException(e);
			}
		}

		return input;
	}
}
