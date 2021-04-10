-- 删除表
DROP DATABASE IF EXISTS SPRING_BOOT_SHIRO;
-- 创建数据库 SpringBoot整合Shiro项目数据库
CREATE DATABASE IF NOT EXISTS SPRING_BOOT_SHIRO;
-- 使用数据库
USE SPRING_BOOT_SHIRO;
-- 创建表
CREATE TABLE IF NOT EXISTS USER(
    ID INT(20) NOT NULL COMMENT 'ID',
    USERNAME VARCHAR(30) NOT NULL COMMENT '用户名',
    PASSWORD VARCHAR(30) NOT NULL COMMENT '密码',
    SALT VARCHAR(30) DEFAULT NULL COMMENT '盐值',
    ROLE VARCHAR(30) NOT NULL COMMENT '角色',
    PERMISSION VARCHAR(30) NOT NULL COMMENT '权限',
    PRIMARY KEY(ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='USER表';
-- 插入数据
INSERT INTO USER(ID, USERNAME, PASSWORD, ROLE, PERMISSION) VALUES(1,'admin','123456','admin','admin:del');
INSERT INTO USER(ID, USERNAME, PASSWORD, ROLE, PERMISSION) VALUES(2,'zhangsan','123456','staff','staff:add');
INSERT INTO USER(ID, USERNAME, PASSWORD, ROLE, PERMISSION) VALUES(3,'xiaoming','123456','user','user:info');