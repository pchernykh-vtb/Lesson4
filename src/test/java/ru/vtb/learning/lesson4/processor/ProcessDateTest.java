package ru.vtb.learning.lesson4.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vtb.learning.lesson4.model.PlainLogItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessDateTest {
    @Test
    public void processNullSuccess(){
        Processable processor = new ProcessDate();

        List<PlainLogItem> plainLogListSrc = new ArrayList<>();
        String plainLogString;
        PlainLogItem plainLogItem;

        plainLogString = "Login ФАМИЛИЯ ИМЯ ОТЧЕСТВО  тип_приложения";
        plainLogItem = new PlainLogItem(plainLogString);
        plainLogListSrc.add(plainLogItem);

        List<PlainLogItem> plainLogListRes = processor.process(plainLogListSrc);

        Assertions.assertEquals(0, plainLogListRes.size(),"Запись с пустой датой убрана из списка");
    }

    @Test
    public void processSuccess(){
        Processable processor = new ProcessDate();

        List<PlainLogItem> plainLogListSrc = new ArrayList<>();
        String plainLogString;
        PlainLogItem plainLogItem;

        plainLogString = "Login ФАМИЛИЯ ИМЯ ОТЧЕСТВО 29.05.2024 тип_приложения";
        plainLogItem = new PlainLogItem(plainLogString);
        plainLogListSrc.add(plainLogItem);

        List<PlainLogItem> plainLogListRes = processor.process(plainLogListSrc);

        Assertions.assertEquals(1, plainLogListRes.size(),"Запись с нормальной датой осталась в списке");
    }
}
