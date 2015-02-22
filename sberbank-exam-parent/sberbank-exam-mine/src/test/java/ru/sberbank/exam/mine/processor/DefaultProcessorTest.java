package ru.sberbank.exam.mine.processor;

import ru.sberbank.exam.mine.process.WaitProcess;


public class DefaultProcessorTest extends ProcessorTest {

	@Override
	protected Processor setSut() {
		return new DefaultProcessor(new WaitProcess(0));
	}

}
