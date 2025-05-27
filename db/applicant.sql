create table applicant
(
    id             bigint       not null comment 'ID'
        primary key,
    name           varchar(255) null comment '用户名称',
    email          varchar(255) null comment '邮件',
    gender         tinyint(1)   null comment '性别(TRUE-男, FALSE-女, NULL-未知)',
    birth_time     datetime     null comment '出生日期',
    graduate_time  datetime     null comment '毕业日期',
    phone          varchar(255) null comment '联系电话',
    resume_path    varchar(255) null comment '简历路径',
    create_time    datetime     null comment '创建时间',
    create_by      bigint       null comment '创建人',
    update_time    datetime     null comment '更新时间',
    update_by      bigint       null comment '更新人',
    version        bigint       null comment '版本',
    deleted        tinyint(1)   null comment '是否删除标记',
    address        varchar(255) null comment '地址',
    evaluation     text         null comment '评价',
    interviewer_id bigint       null comment '面试官Id',
    interview_time datetime     null comment '应聘时间'
)
    comment '应聘者表';

