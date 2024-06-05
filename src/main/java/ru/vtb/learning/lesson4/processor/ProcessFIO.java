package ru.vtb.learning.lesson4.processor;

import org.springframework.stereotype.Component;
import ru.vtb.learning.lesson4.logging.LogTransformation;
import ru.vtb.learning.lesson4.model.PlainLogItem;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProcessFIO implements Processable{
    private Processable nextProcessor;

    public ProcessFIO() {
    }
    @Override
    public void setNextProcessor(Processable nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    @LogTransformation
    public List<PlainLogItem> process(List<PlainLogItem> plainLogList) {
        for (PlainLogItem item : plainLogList) {
            item.setFio(
                    Arrays.stream(item.getFio().split("\\s+"))
                            .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                            .collect(Collectors.joining(" "))
            );
        }

        if (nextProcessor != null) {
            plainLogList = nextProcessor.process(plainLogList);
        }

        return plainLogList;
    }
}
