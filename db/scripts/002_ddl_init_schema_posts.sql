create table if not exists auto_posts
(
    id    serial    primary    key,
    description    text,
    created    timestamp,
    auto_user_id int references auto_users(id)
)