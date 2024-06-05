package ru.vtb.learning.lesson4.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import ru.vtb.learning.lesson4.repository.entity.UserEntity;

@SpringBootTest
@Transactional
@Rollback
public class SaveToDbTest {
    @Autowired
    UserCrudRepository userRepo;
    @Autowired
    LoginCrudRepository loginRepo;

    @Test
    public void userSaveSuccess(){
        String login = "логин9";
        String fio = "ФАМИЛИЯ ИМЯ ОТЧЕСТВО";
        UserEntity user = new UserEntity(login, fio);
        userRepo.save(user);

        final UserEntity userRes = userRepo.findByUsername(login);
        Assertions.assertAll(
                () -> Assertions.assertEquals(login, login),
                () -> Assertions.assertEquals(fio, fio)
        );
    }
}
