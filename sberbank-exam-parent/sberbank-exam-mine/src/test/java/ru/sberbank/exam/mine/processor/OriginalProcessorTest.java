package ru.sberbank.exam.mine.processor;

import ru.sberbank.exam.mine.process.WaitProcess;

public class OriginalProcessorTest extends ProcessorTest {

	@Override
	protected Processor setSut() {
		return new OriginalProcessor(new WaitProcess(0));
	}

}
