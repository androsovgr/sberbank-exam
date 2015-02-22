package ru.sberbank.exam.mine.processor;

import ru.sberbank.exam.mine.process.Process;

public class DefaultProcessor implements Processor {

	private Process process;

	public DefaultProcessor(Process process) {
		super();
		this.process = process;
	}

	@Override
	public byte[] process(byte[] input) {
		return process.process(input);
	}

	@Override
	public Process getProcess() {
		return process;
	}

}
