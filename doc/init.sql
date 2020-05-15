DROP DATABASE
    IF EXISTS `scaffolding`;

CREATE DATABASE `scaffolding`
    CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_bin';

USE `scaffolding`;

DROP TABLE
    IF EXISTS `t_scaffolding_account`; -- t_项目名_模块_表名

CREATE TABLE `t_scaffolding_account`
(
    `t_id`        BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `account`     VARCHAR(255) NOT NULL COMMENT '账号',
    `password`    VARCHAR(255) NOT NULL COMMENT '密码',
    -- 公共字段 --
    `create_time` DATETIME     NOT NULL COMMENT '创建时间',
    `update_time` DATETIME     NOT NULL COMMENT '更新时间',
    `operator_id` VARCHAR(255) NOT NULL COMMENT '操作人ID',
    `deleted`     TINYINT      NOT NULL DEFAULT '0', -- 0：未删除 1：删除
    PRIMARY KEY (`t_id`),
    UNIQUE KEY (`account`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8;

INSERT INTO t_scaffolding_account (account, password, create_time, update_time, operator_id)
VALUES ('admin', 'admin', NOW(), NOW(), 0)
