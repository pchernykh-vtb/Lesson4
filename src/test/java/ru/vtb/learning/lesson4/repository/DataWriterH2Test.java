package ru.vtb.learning.lesson4.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;
import ru.vtb.learning.lesson4.model.PlainLogItem;
import ru.vtb.learning.lesson4.repository.entity.LoginEntity;
import ru.vtb.learning.lesson4.repository.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Rollback
public class DataWriterH2Test {
    @Mock
    UserCrudRepository userRepo;
    @Mock
    LoginCrudRepository loginRepo;
    @InjectMocks
    DataWriterH2 dataWriter;
    @Test
    public void processNoDbUserSuccess(){
        List<PlainLogItem> plainLogListSrc = new ArrayList<>();
        String plainLogString;
        PlainLogItem plainLogItem;

        plainLogString = "Login ФАМИЛИЯ ИМЯ ОТЧЕСТВО 29.05.2024 тип_приложения";
        plainLogItem = new PlainLogItem(plainLogString);
        plainLogListSrc.add(plainLogItem);

        // Имитируем, что в БД пользователя не нашли, чтобы он создался.
        when(userRepo.findByUsername(plainLogItem.getLogin())).thenReturn(null);
        // Имитируем сохранение.
        when(userRepo.save(Mockito.any(UserEntity.class))).thenReturn(null);
        when(loginRepo.save(Mockito.any(LoginEntity.class))).thenReturn(null);

        dataWriter.writeData(plainLogListSrc);

        // Проверяем, что сохранение вызывалось.
        verify(userRepo, Mockito.times(1)).save(Mockito.any(UserEntity.class));
        verify(loginRepo, Mockito.times(1)).save(Mockito.any(LoginEntity.class));
    }

    @Test
    public void processTwoLogsOfOneUserWithCacheSuccess(){
        List<PlainLogItem> plainLogListSrc = new ArrayList<>();
        String plainLogString;
        PlainLogItem plainLogItem;

        plainLogString = "Login ФАМИЛИЯ ИМЯ ОТЧЕСТВО 29.05.2024 тип_приложения";
        plainLogItem = new PlainLogItem(plainLogString);
        // Должен не найти пользователя в БД, создать и сохранить.
        plainLogListSrc.add(plainLogItem);
        // Пользователь тот же - найдётся в кэше и сохранится только факт входа.
        plainLogListSrc.add(plainLogItem);

        // Имитируем, что в БД пользователя не нашли, чтобы он создался.
        when(userRepo.findByUsername(plainLogItem.getLogin())).thenReturn(null);
        // Имитируем сохранение.
        when(userRepo.save(Mockito.any(UserEntity.class))).thenReturn(null);
        when(loginRepo.save(Mockito.any(LoginEntity.class))).thenReturn(null);

        dataWriter.writeData(plainLogListSrc);

        // Проверяем, что сохранение вызывалось.
        verify(userRepo, Mockito.times(1)).save(Mockito.any(UserEntity.class));
        verify(loginRepo, Mockito.times(2)).save(Mockito.any(LoginEntity.class));
    }
}
