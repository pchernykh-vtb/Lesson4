package ru.vtb.learning.lesson4.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vtb.learning.lesson4.model.PlainLogItem;

import java.util.ArrayList;
import java.util.List;

public class ProcessApplicationTest {
    @Test
    public void processAddOtherSuccess(){
        Processable processor = new ProcessApplication();

        List<PlainLogItem> plainLogListSrc = new ArrayList<>();
        List<PlainLogItem> plainLogListTmp = new ArrayList<>();
        String plainLogString;
        PlainLogItem plainLogItem;
        PlainLogItem plainLogItemTmp;

        plainLogString = "Login ФАМИЛИЯ ИМЯ ОТЧЕСТВО 29.05.2024 тип_приложения";
        plainLogItem = new PlainLogItem(plainLogString);
        plainLogItemTmp = new PlainLogItem(plainLogString);
        plainLogListSrc.add(plainLogItem);
        plainLogListTmp.add(plainLogItemTmp);

        List<PlainLogItem> plainLogListRes = processor.process(plainLogListTmp);

        Assertions.assertEquals("other: " + plainLogListSrc.get(0).getApplication(), plainLogListRes.get(0).getApplication(), "Добавление префикса 'other: '");
    }

    @Test
    public void processWebSuccess(){
        Processable processor = new ProcessApplication();

        List<PlainLogItem> plainLogListSrc = new ArrayList<>();
        List<PlainLogItem> plainLogListTmp = new ArrayList<>();
        String plainLogString;
        PlainLogItem plainLogItem;
        PlainLogItem plainLogItemTmp;

        plainLogString = "Login ФАМИЛИЯ ИМЯ ОТЧЕСТВО 29.05.2024 web";
        plainLogItem = new PlainLogItem(plainLogString);
        plainLogItemTmp = new PlainLogItem(plainLogString);
        plainLogListSrc.add(plainLogItem);
        plainLogListTmp.add(plainLogItemTmp);

        List<PlainLogItem> plainLogListRes = processor.process(plainLogListTmp);

        Assertions.assertEquals(plainLogListSrc.get(0).getApplication(), plainLogListRes.get(0).getApplication(), "Приложение 'web' не изменилось");
    }

    @Test
    public void processMobileSuccess(){
        Processable processor = new ProcessApplication();

        List<PlainLogItem> plainLogListSrc = new ArrayList<>();
        List<PlainLogItem> plainLogListTmp = new ArrayList<>();
        String plainLogString;
        PlainLogItem plainLogItem;
        PlainLogItem plainLogItemTmp;

        plainLogString = "Login ФАМИЛИЯ ИМЯ ОТЧЕСТВО 29.05.2024 mobile";
        plainLogItem = new PlainLogItem(plainLogString);
        plainLogItemTmp = new PlainLogItem(plainLogString);
        plainLogListSrc.add(plainLogItem);
        plainLogListTmp.add(plainLogItemTmp);

        List<PlainLogItem> plainLogListRes = processor.process(plainLogListTmp);

        Assertions.assertEquals(plainLogListSrc.get(0).getApplication(), plainLogListRes.get(0).getApplication(), "Приложение 'mobile' не изменилось");
    }

    @Test
    public void processNullSuccess(){
        Processable processor = new ProcessApplication();

        List<PlainLogItem> plainLogListSrc = new ArrayList<>();
        List<PlainLogItem> plainLogListTmp = new ArrayList<>();
        String plainLogString;
        PlainLogItem plainLogItem;
        PlainLogItem plainLogItemTmp;

        plainLogString = "Login ФАМИЛИЯ ИМЯ ОТЧЕСТВО 29.05.2024";
        plainLogItem = new PlainLogItem(plainLogString);
        plainLogItemTmp = new PlainLogItem(plainLogString);
        plainLogListSrc.add(plainLogItem);
        plainLogListTmp.add(plainLogItemTmp);

        List<PlainLogItem> plainLogListRes = processor.process(plainLogListTmp);

        Assertions.assertEquals("other: ", plainLogListRes.get(0).getApplication(), "Пустое приложение получило префикс 'other: '");
    }

    @Test
    public void processAlreadyOtherSuccess(){
        Processable processor = new ProcessApplication();

        List<PlainLogItem> plainLogListSrc = new ArrayList<>();
        List<PlainLogItem> plainLogListTmp = new ArrayList<>();
        String plainLogString;
        PlainLogItem plainLogItem;
        PlainLogItem plainLogItemTmp;

        plainLogString = "Login ФАМИЛИЯ ИМЯ ОТЧЕСТВО 29.05.2024 other: ";
        plainLogItem = new PlainLogItem(plainLogString);
        plainLogItemTmp = new PlainLogItem(plainLogString);
        plainLogListSrc.add(plainLogItem);
        plainLogListTmp.add(plainLogItemTmp);

        List<PlainLogItem> plainLogListRes = processor.process(plainLogListTmp);

        Assertions.assertEquals("other: ", plainLogListRes.get(0).getApplication(), "Приложение 'other: ' не изменилось");
    }
}
