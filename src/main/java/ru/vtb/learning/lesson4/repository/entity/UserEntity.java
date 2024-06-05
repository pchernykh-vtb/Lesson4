package ru.vtb.learning.lesson4.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class UserEntity implements Comparable<UserEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int     id;
    @Column(name = "username", nullable = false)
    private String  username;
    @Column(name = "fio")
    private String  fio;

    protected UserEntity() {}

    public UserEntity(String username, String fio) {
        this.username = username;
        this.fio = fio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity that)) return false;
        return Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }

    @Override
    public int compareTo(UserEntity o) {
        return this.username.compareTo(o.getUsername());
    }
}
