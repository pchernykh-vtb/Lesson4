package ru.vtb.learning.lesson4.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vtb.learning.lesson4.repository.entity.UserEntity;

public interface UserCrudRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByUsername(String userName);
}
