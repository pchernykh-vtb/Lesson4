package ru.vtb.learning.lesson4.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vtb.learning.lesson4.repository.entity.LoginEntity;

public interface LoginCrudRepository extends CrudRepository<LoginEntity, Integer> {
}
