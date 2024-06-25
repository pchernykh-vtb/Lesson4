package ru.vtb.learning.lesson4.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vtb.learning.lesson4.model.PlainLogItem;

import java.util.List;

public class DataReaderFromFilesTest {
    @Test
    public void dataReadingSuccess() {
        // Из двух файлов папки считывается 5 записей.
        DataReader dataReader = new DataReaderFromFiles("src/test/resources/SourceLogFiles");

        List<PlainLogItem> plainLogList = dataReader.getData();

        Assertions.assertEquals(5, plainLogList.size());
    }

    @Test
    public void dataReadingNoDirSuccess() {
        // Папки нет или папка есть, но файлов нет - 0 записей считано.
        DataReader dataReader = new DataReaderFromFiles("wrong/path");

        List<PlainLogItem> plainLogList = dataReader.getData();

        Assertions.assertEquals(0, plainLogList.size());
    }
}
