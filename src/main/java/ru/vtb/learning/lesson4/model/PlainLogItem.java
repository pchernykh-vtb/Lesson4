package ru.vtb.learning.lesson4.model;

import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class PlainLogItem {
    private String login;
    private String fio;
    private Date accessDate;
    private String application;

    public PlainLogItem(String str) {
        this.login = "";
        this.fio = "";
        this.application = "";
        // Если строка журнала со странным форматом, считаем из неё что сможем.
        try {
            String[] strArr= str.split(" ", 6);
            this.login = strArr[0];
            this.fio = strArr[1] + " " + strArr[2] + " " + strArr[3];
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
                this.accessDate = format.parse(strArr[4]);
            } catch (ParseException exc) {}
            this.application = strArr[5];
        } catch (ArrayIndexOutOfBoundsException exc) {}
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return "PlainLog{" +
                "login='" + login + '\'' +
                ", fio='" + fio + '\'' +
                ", accessDate=" + (accessDate == null? "'null'" : dateFormat.format(accessDate)) +
                ", application='" + application + '\'' +
                '}';
    }
}
