-- auto-generated definition
create table user
(
    id       bigint               not null comment '主键'
        primary key,
    username varchar(255)         not null comment '用户名',
    name     varchar(255)         null comment '姓名',
    password varchar(255)         not null comment '密码',
    type     int                  not null comment '类型（10：管理员, 20:面试官）',
    deleted  tinyint(1) default 0 null comment '是否删除标记',
    constraint idx_username
        unique (username)
)
    comment '用户表';

