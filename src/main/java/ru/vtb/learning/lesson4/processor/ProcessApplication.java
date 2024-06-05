package ru.vtb.learning.lesson4.processor;

import org.springframework.stereotype.Component;
import ru.vtb.learning.lesson4.logging.LogTransformation;
import ru.vtb.learning.lesson4.model.PlainLogItem;

import java.util.List;

@Component
public class ProcessApplication implements Processable{
    private Processable nextProcessor;

    public ProcessApplication() {
    }
    @Override
    public void setNextProcessor(Processable nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    @LogTransformation
    public List<PlainLogItem> process(List<PlainLogItem> plainLogList) {
        String prefix = "other: ";
        for (PlainLogItem item : plainLogList) {
            if (item.getApplication() == null) {
                item.setApplication("");
            }
            if (!item.getApplication().equals("web")
                    && !item.getApplication().equals("mobile")
                    && !item.getApplication().startsWith(prefix)
            ) {
                item.setApplication(prefix + item.getApplication());
            }
        }

        if (nextProcessor != null) {
            plainLogList = nextProcessor.process(plainLogList);
        }

        return plainLogList;
    }
}
