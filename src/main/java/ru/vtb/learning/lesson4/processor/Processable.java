package ru.vtb.learning.lesson4.processor;

import ru.vtb.learning.lesson4.model.PlainLogItem;

import java.util.List;

public interface Processable {
    void setNextProcessor(Processable nextProcessor);
    List<PlainLogItem> process(List<PlainLogItem> plainLogList);
}
