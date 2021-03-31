-- 创建数据库PRACTICE
CREATE DATABASE PRACTICE DEFAULT CHARACTER SET UTF8;
-- 选中数据库
USE PRACTICE;
-- 创建表USER
CREATE TABLE IF NOT EXISTS PRACTICE.USER(
    ID INT(11) NOT NULL COMMENT '用户ID',
    USERNAME VARCHAR(64) NOT NULL COMMENT '用户名',
    PASSWORD VARCHAR(64) NOT NULL COMMENT '密码',
    PRIMARY KEY (ID)
)ENGINE = INNODB DEFAULT CHARSET = UTF8 COMMENT = '用户表';

-- 插入数据
INSERT INTO PRACTICE.USER(ID,USERNAME,PASSWORD) VALUES(1,'hyd','hyd');

--创建
create table PERSISTENT_LOGINS (
    username varchar(64) not null,
    series varchar(64) primary key,
    token varchar(64) not null,
    last_used timestamp not null
);