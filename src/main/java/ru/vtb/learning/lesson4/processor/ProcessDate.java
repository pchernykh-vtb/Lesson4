package ru.vtb.learning.lesson4.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.vtb.learning.lesson4.logging.LogTransformation;
import ru.vtb.learning.lesson4.model.PlainLogItem;

import java.util.List;
import java.util.ListIterator;

@Component
@Slf4j
public class ProcessDate implements Processable{
    private Processable nextProcessor;

    public ProcessDate() {
    }
    @Override
    public void setNextProcessor(Processable nextProcessor) {
        this.nextProcessor = nextProcessor;
    }

    @Override
    @LogTransformation("C:\\app\\Lesson4.log")
    public List<PlainLogItem> process(List<PlainLogItem> plainLogList) {
        ListIterator<PlainLogItem> iterator = plainLogList.listIterator();
        while (iterator.hasNext()) {
            PlainLogItem item = iterator.next();
            if (item.getAccessDate() == null) {
                log.info("Пустая дата в строке: {}", item);
                iterator.remove();  // Убираем из списка, чтобы не пыталось сохраниться в БД.
            }
        }

        if (nextProcessor != null) {
            plainLogList = nextProcessor.process(plainLogList);
        }

        return plainLogList;
    }
}
