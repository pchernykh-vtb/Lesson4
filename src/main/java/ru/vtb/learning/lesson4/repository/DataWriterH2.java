package ru.vtb.learning.lesson4.repository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;
import ru.vtb.learning.lesson4.model.PlainLogItem;
import ru.vtb.learning.lesson4.repository.entity.LoginEntity;
import ru.vtb.learning.lesson4.repository.entity.UserEntity;

import java.util.*;

@Component
public class DataWriterH2 implements DataWriter{
    UserCrudRepository userRepo;
    LoginCrudRepository loginRepo;
    public DataWriterH2(UserCrudRepository userRepo, LoginCrudRepository loginRepo) {
        this.userRepo = userRepo;
        this.loginRepo = loginRepo;
    }

    @Override
    @Transactional
    public void writeData(List<PlainLogItem> plainLogList) {
        Map<String, UserEntity> userList = new HashMap<>();

        for (PlainLogItem plainLogItem : plainLogList) {
            UserEntity user;
            // Проверим в кэше.
            user = userList.get(plainLogItem.getLogin());
            if (user == null) {
                // Проверим в БД.
                user = userRepo.findByUsername(plainLogItem.getLogin());
            }
            if (user == null) {
                // Создадим нового.
                user = new UserEntity(plainLogItem.getLogin(), plainLogItem.getFio());
                userRepo.save(user);
            }
            // Занесём в кэш.
            userList.put(user.getUsername(), user);

            LoginEntity login = new LoginEntity(plainLogItem.getAccessDate(), user, plainLogItem.getApplication());
            loginRepo.save(login);
        }
    }
}
