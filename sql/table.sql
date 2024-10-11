-- auto-generated definition
create table user
(
    id           bigint auto_increment comment '用户id'
        primary key,
    username     varchar(256)                       null comment '用户名',
    userAccount  varchar(256)                       null comment '用户账号',
    avatarUrl    varchar(1024)                      null comment '用户头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       not null comment '用户密码',
    phone        varchar(128)                       null comment '手机号码',
    email        varchar(512)                       null comment '邮箱',
    userStatus   int      default 0                 not null comment '状态(0正常)',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null comment '更新时间',
    isDelete     tinyint  default 0                 not null comment '是否删除',
    userRole     tinyint  default 0                 not null comment '角色(0用户，1管理员)',
    plantCode    varchar(512)                       null comment '编号'
)
    comment '用户表';

