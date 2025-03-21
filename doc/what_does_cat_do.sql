# 用户信息表
CREATE TABLE `user_profile`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',

    `appleId`         VARCHAR(256) NOT NULL DEFAULT ''     COMMENT '苹果ID',
    `nickname`         VARCHAR(64) NOT NULL DEFAULT ''     COMMENT '昵称',
    `avatar`       VARCHAR(512) NOT NULL DEFAULT ''     COMMENT '头像',
    `email`       VARCHAR(512) NOT NULL DEFAULT ''     COMMENT '邮箱',
    `gender`       INT(11) NOT NULL DEFAULT '0'     COMMENT '性别 0-未知 1-男 2-女',
    `address`       VARCHAR(512) NOT NULL DEFAULT ''     COMMENT '地址',
    `relationShipStatus`       INT(11) NOT NULL DEFAULT '0'     COMMENT '感情状态 参考RelationshipStatusEnum',

    `status`       TINYINT(4)   NOT NULL DEFAULT '1'    COMMENT '0:不可用 1:可用',
    `dbctime`      DATETIME(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '创建时间',
    `dbutime`      DATETIME(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '更新时间',
    PRIMARY KEY    (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

