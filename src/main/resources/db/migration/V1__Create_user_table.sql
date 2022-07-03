create table if   NOT EXISTS USER
(
    name         varchar(50)  null,
    account_id   varchar(100) null,
    id           int auto_increment
        primary key,
    token        char(36)     null,
    gmt_create   bigint       null,
    gmt_modified bigint       null
);
