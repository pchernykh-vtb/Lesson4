package ru.vtb.learning.lesson4.repository;

import ru.vtb.learning.lesson4.model.PlainLogItem;

import java.util.List;

public interface DataWriter {
    void writeData(List<PlainLogItem> plainLogList);
}
