package ru.vtb.learning.lesson4.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Around("@annotation(LogTransformation)")
    public void log(ProceedingJoinPoint joinPoint) throws Throwable{
        Date currDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String logStr;
        String logFileName = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(LogTransformation.class).value();

        currDate = new Date();
        logStr = dateFormat.format(currDate) + " Запущен метод " + joinPoint.getSignature().toShortString() + " с параметрами " + Arrays.toString(joinPoint.getArgs());
        log(logFileName, logStr);

        Object res = joinPoint.proceed();

        currDate = new Date();
        logStr = dateFormat.format(currDate) + " Завершён метод " + joinPoint.getSignature().toShortString() + " с результатом " + res;
        log(logFileName, logStr);
    }

    private void log(String logFileName, String logStr) {
        if (logFileName.isBlank()) {
            log.info(logStr);
        } else {
            try {
                FileWriter writer = new FileWriter(logFileName);
                writer.write(logStr);
                writer.close();
            } catch (IOException exc) {
                log.info(logStr);
            }
        }
    }
}
