package ru.vtb.learning.lesson4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.vtb.learning.lesson4.model.PlainLogItem;
import ru.vtb.learning.lesson4.processor.Processable;
import ru.vtb.learning.lesson4.repository.DataReader;
import ru.vtb.learning.lesson4.repository.DataWriter;

import java.util.List;

@SpringBootApplication
public class Lesson4Application {
    private final ApplicationContext context;
    private final DataReader dataReader;
    @Value("${processor.order}")
    private String processorOrder;
    private final DataWriter dataWriter;

    public Lesson4Application(ApplicationContext context, DataReader dataReader, DataWriter dataWriter) {
        this.context = context;
        this.dataReader = dataReader;
        this.dataWriter = dataWriter;
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Lesson4Application.class, args);

        ((Lesson4Application) context.getBean("lesson4Application")).run(args);
    }

    public void run(String...args) {
        List<PlainLogItem> plainLogList;
        Processable processor = null;

        // Выстраиваем очерёдность обработки по свойству.
        // Так как цепочка строится по принципу стека, то перебираем в обратном порядке.
        String[] processorOrderList = processorOrder.split(",");
        for (int idx = processorOrderList.length - 1; idx >= 0; idx--) {
            Processable oldProcessor = processor;
            processor = (Processable) context.getBean(processorOrderList[idx]);
            processor.setNextProcessor(oldProcessor);
        }

        plainLogList = dataReader.getData();

        if (processor != null) {
            processor.process(plainLogList);
        }

        dataWriter.writeData(plainLogList);
    }
}
