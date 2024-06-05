create table if not exists users (
    id              int auto_increment primary key,
    username        varchar not null,
    fio             varchar
);

create unique index if not exists username_idx on users(username);

create table if not exists logins
(
    id              int auto_increment primary key,
    access_date     timestamp,
    user_id         int references users (id),
    application     varchar
);