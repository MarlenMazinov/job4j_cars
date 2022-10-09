create table if not exists auto_user
(
    id    serial    primary    key,
    login    varchar(255) unique not null,
    password text
    )