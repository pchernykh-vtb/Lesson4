package ru.vtb.learning.lesson4.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PlainLogItemTest {
    @Test
    public void constructorParseSuccess() throws ParseException{
        String login = "Login";
        String fio = "ФАМИЛИЯ ИМЯ ОТЧЕСТВО";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        final Date accessDate = dateFormat.parse("29.05.2024");
        String application = "тип_приложения";

        String strToParse = login + " " + fio + " " + dateFormat.format(accessDate) + " " + application;

        PlainLogItem plainLogItem = new PlainLogItem(strToParse);

        Assertions.assertAll(
                "Parsing error in PlainLogItem constructor.",
                () -> Assertions.assertEquals(login,        plainLogItem.getLogin(),        "Разбор логина"),
                () -> Assertions.assertEquals(fio,          plainLogItem.getFio(),          "Разбор ФИО"),
                () -> Assertions.assertEquals(accessDate,   plainLogItem.getAccessDate(),   "Разбор даты"),
                () -> Assertions.assertEquals(application,  plainLogItem.getApplication(),  "Разбор приложения")
        );
    }

    @Test
    public void constructorParseFail() throws ParseException{
        String login = "Login";
        String fio = "ФАМИЛИЯ ИМЯ ОТЧЕСТВО";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        final Date accessDateActual = dateFormat.parse("29.05.2024");
        final Date accessDateExpected = dateFormat.parse("30.05.2024");
        String application = "тип_приложения";

        String strToParse = login + "1 " + fio + "1 " + dateFormat.format(accessDateActual) + " 1" + application;

        PlainLogItem plainLogItem = new PlainLogItem(strToParse);

        Assertions.assertAll(
                "Parsing error in PlainLogItem constructor.",
                () -> Assertions.assertNotEquals(login,              plainLogItem.getLogin(),       "Разбор логина"),
                () -> Assertions.assertNotEquals(fio,                plainLogItem.getFio(),         "Разбор ФИО"),
                () -> Assertions.assertNotEquals(accessDateExpected, plainLogItem.getAccessDate(),  "Разбор даты"),
                () -> Assertions.assertNotEquals(application,        plainLogItem.getApplication(), "Разбор приложения")
        );
    }
}