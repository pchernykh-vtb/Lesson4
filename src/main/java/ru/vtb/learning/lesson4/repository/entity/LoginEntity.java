package ru.vtb.learning.lesson4.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "LOGINS")
@Getter
@Setter
public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int         id;
    @Column(name = "access_date", nullable = false)
    private Date        accessDate;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity  userId;
    @Column(name = "application", nullable = false)
    private String      application;

    protected LoginEntity() {}

    public LoginEntity(Date accessDate, UserEntity userId, String application) {
        this.accessDate = accessDate;
        this.userId = userId;
        this.application = application;
    }
}
