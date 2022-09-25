create table if not exists auto_users
(
    id    serial    primary    key,
    login    varchar(255) unique,
    password text
    )