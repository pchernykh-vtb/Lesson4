package ru.vtb.learning.lesson4;

import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import ru.vtb.learning.lesson4.model.PlainLogItem;
import ru.vtb.learning.lesson4.processor.Processable;
import ru.vtb.learning.lesson4.repository.*;
import ru.vtb.learning.lesson4.repository.entity.LoginEntity;
import ru.vtb.learning.lesson4.repository.entity.UserEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
@Rollback
@Transactional
class Lesson4ApplicationTests {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private DataReader dataReader = new DataReaderFromFiles("src/test/resources/SourceLogFiles");
    @Value("${processor.order}")
    private String processorOrder;
    @Autowired
    private DataWriter dataWriter;
    @Autowired
    UserCrudRepository userRepo;
    @Autowired
    LoginCrudRepository loginRepo;

    @Test
    void contextLoads() throws ParseException {
        List<UserEntity> userListDbTemp = Lists.newArrayList(userRepo.findAll());
        List<LoginEntity> loginListDbTemp = Lists.newArrayList(loginRepo.findAll());

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

        processor.process(plainLogList);

        dataWriter.writeData(plainLogList);

        List<UserEntity> userListEtalon = new ArrayList<>();

        UserEntity user;
        user = new UserEntity("Login", "Фамилия Имя Отчество");
        userListEtalon.add((user));
        user = new UserEntity("Логин2", "Фамилия2 Имя2 Отчество2");
        userListEtalon.add((user));
        user = new UserEntity("Логин3", "Фамилия3 Имя3 Отчество3");
        userListEtalon.add((user));
        Collections.sort(userListEtalon);

        List<UserEntity> userListDb = Lists.newArrayList(userRepo.findAll());
        Collections.sort(userListDb);

        List<LoginEntity> loginListEtalon = new ArrayList<>();

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        LoginEntity login;
        login = new LoginEntity(format.parse("29.05.2024"), null, "other: тип_приложения");
        loginListEtalon.add(login);
        login = new LoginEntity(format.parse("30.05.2024"), null, "other: тип_приложения");
        loginListEtalon.add(login);
        login = new LoginEntity(format.parse("30.05.2024"), null, "web");
        loginListEtalon.add(login);
        login = new LoginEntity(format.parse("31.05.2024"), null, "mobile");
        loginListEtalon.add(login);

        List<LoginEntity> loginListDb = Lists.newArrayList(loginRepo.findAll());

        Assertions.assertAll(
                () -> Assertions.assertTrue(userListEtalon.equals(userListDb), "Совпадение списков пользователей"),
                () -> Assertions.assertTrue(loginListEquals(loginListEtalon, loginListDb), "Совпадение списков фактов входа")
        );
    }

    private boolean loginListEquals(List<LoginEntity> list1, List<LoginEntity> list2) {
        // В общем случае сравнивать факты входа надо в привязке к пользователю.
        // Здесь упрощенное сравнение, поэтому перебором вручную в лоб.
        if (list1.size() != list2.size()) { return false; }

        for(LoginEntity login1: list1) {
            boolean found = false;
            for (LoginEntity login2 : list2) {
                if (login1.getAccessDate().equals(login2.getAccessDate())
                    && login1.getApplication().equals(login2.getApplication())
                ) {
                    found = true;
                    break;
                }
            }
            if (!found) { return false; }
        }
        return true;
    }
}
