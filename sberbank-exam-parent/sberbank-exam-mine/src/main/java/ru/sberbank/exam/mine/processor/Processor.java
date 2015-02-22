package ru.sberbank.exam.mine.processor;

import ru.sberbank.exam.mine.process.Process;

public interface Processor {

	public byte[] process(byte[] input);

	public Process getProcess();
}
