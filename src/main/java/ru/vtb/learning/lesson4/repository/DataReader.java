package ru.vtb.learning.lesson4.repository;

import ru.vtb.learning.lesson4.model.PlainLogItem;

import java.util.List;

public interface DataReader {
    List<PlainLogItem> getData();
}
