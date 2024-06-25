package ru.vtb.learning.lesson4.processor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vtb.learning.lesson4.model.PlainLogItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessFIOTest {
    @Test
    public void processUpperCaseSuccess(){
        Processable processor = new ProcessFIO();

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

        Assertions.assertEquals(
                Arrays.stream(plainLogListSrc.get(0).getFio().split("\\s"))
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                        .collect(Collectors.joining(" "))
                ,plainLogListRes.get(0).getFio()
                ,"ФИО с большой буквы");
    }

    @Test
    public void processLowerCaseSuccess(){
        Processable processor = new ProcessFIO();

        List<PlainLogItem> plainLogListSrc = new ArrayList<>();
        List<PlainLogItem> plainLogListTmp = new ArrayList<>();
        String plainLogString;
        PlainLogItem plainLogItem;
        PlainLogItem plainLogItemTmp;

        plainLogString = "Login фамилия имя отчество 29.05.2024 тип_приложения";
        plainLogItem = new PlainLogItem(plainLogString);
        plainLogItemTmp = new PlainLogItem(plainLogString);
        plainLogListSrc.add(plainLogItem);
        plainLogListTmp.add(plainLogItemTmp);

        List<PlainLogItem> plainLogListRes = processor.process(plainLogListTmp);

        Assertions.assertEquals(
                Arrays.stream(plainLogListSrc.get(0).getFio().split("\\s"))
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                        .collect(Collectors.joining(" "))
                ,plainLogListRes.get(0).getFio()
                ,"фио с большой буквы");
    }

    @Test
    public void processWrongFormatSuccess(){
        Processable processor = new ProcessFIO();

        List<PlainLogItem> plainLogListSrc = new ArrayList<>();
        List<PlainLogItem> plainLogListTmp = new ArrayList<>();
        String plainLogString;
        PlainLogItem plainLogItem;
        PlainLogItem plainLogItemTmp;

        plainLogString = "Login фамилия имя 29.05.2024 тип_приложения";
        plainLogItem = new PlainLogItem(plainLogString);
        plainLogItemTmp = new PlainLogItem(plainLogString);
        plainLogListSrc.add(plainLogItem);
        plainLogListTmp.add(plainLogItemTmp);

        List<PlainLogItem> plainLogListRes = processor.process(plainLogListTmp);

        Assertions.assertEquals(
                Arrays.stream(plainLogListSrc.get(0).getFio().split("\\s"))
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                        .collect(Collectors.joining(" "))
                ,plainLogListRes.get(0).getFio()
                ,"фи с большой буквы");
    }

    @Test
    public void processMixedCaseSuccess(){
        Processable processor = new ProcessFIO();

        List<PlainLogItem> plainLogListSrc = new ArrayList<>();
        List<PlainLogItem> plainLogListTmp = new ArrayList<>();
        String plainLogString;
        PlainLogItem plainLogItem;
        PlainLogItem plainLogItemTmp;

        plainLogString = "Login Фамилия иМЯ оТчеСТВО 29.05.2024 тип_приложения";
        plainLogItem = new PlainLogItem(plainLogString);
        plainLogItemTmp = new PlainLogItem(plainLogString);
        plainLogListSrc.add(plainLogItem);
        plainLogListTmp.add(plainLogItemTmp);

        List<PlainLogItem> plainLogListRes = processor.process(plainLogListTmp);

        Assertions.assertEquals(
                Arrays.stream(plainLogListSrc.get(0).getFio().split("\\s"))
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase())
                        .collect(Collectors.joining(" "))
                ,plainLogListRes.get(0).getFio()
                ,"фИО с большой буквы");
    }
}
