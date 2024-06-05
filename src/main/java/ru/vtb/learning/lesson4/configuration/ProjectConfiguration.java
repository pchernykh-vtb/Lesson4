package ru.vtb.learning.lesson4.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "ru.vtb.learning.lesson4")
@EnableAspectJAutoProxy
public class ProjectConfiguration {
}
