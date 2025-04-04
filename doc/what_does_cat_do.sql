# 用户信息表
CREATE TABLE `user_profile`
(
    `id`           BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',

    `appleId`         VARCHAR(256) NOT NULL DEFAULT ''     COMMENT '苹果ID',
    `username`         VARCHAR(256) NOT NULL DEFAULT ''     COMMENT '用户名称(苹果的名称, 固定值)',
    `email`       VARCHAR(512) NOT NULL DEFAULT ''     COMMENT '邮箱',

    `nickname`         VARCHAR(64) NOT NULL DEFAULT ''     COMMENT '昵称',
    `phone`         VARCHAR(64) NOT NULL DEFAULT ''     COMMENT '电话',
    `avatar`       VARCHAR(512) NOT NULL DEFAULT ''     COMMENT '头像URL',
    `gender`       INT(11) NOT NULL DEFAULT '0'     COMMENT '性别 0-未知 1-男 2-女',
    `birthTime`       BIGINT(20) NOT NULL DEFAULT '0'     COMMENT '出生时间(可以精确到毫秒)',
    `address`       VARCHAR(512) NOT NULL DEFAULT ''     COMMENT '地址',
    `birthPlace`       VARCHAR(512) NOT NULL DEFAULT ''     COMMENT '出生地址',
    `relationShipStatus`       INT(11) NOT NULL DEFAULT '0'     COMMENT '感情状态 参考RelationshipStatusEnum',

    `status`       TINYINT(4)   NOT NULL DEFAULT '1'    COMMENT '0:不可用 1:可用',
    `dbctime`      DATETIME(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '创建时间',
    `dbutime`      DATETIME(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '更新时间',
    PRIMARY KEY    (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

# 用户权益表
CREATE TABLE `user_right` (
    `id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '权益ID',
    `userId` BIGINT(20) UNSIGNED NOT NULL COMMENT '用户ID',
    `transactionId` VARCHAR(128) NOT NULL DEFAULT '' COMMENT '交易ID',

    `type` INT(11) NOT NULL COMMENT '权益类型 1-包月订阅 2-连续包月订阅',
    `name` VARCHAR(64) NOT NULL DEFAULT '' COMMENT '权益名称',
    `paymentTime` DATETIME(3) COMMENT '付费时间',
    `paymentAmount` DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT '付费金额',
    `paymentMethod` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '支付方式',

    `startTime` DATETIME(3) NOT NULL COMMENT '权益开始时间',
    `expireTime` DATETIME(3) NOT NULL COMMENT '权益到期时间',
    `autoRenew` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '是否自动续费 0-否 1-是',
    `remark` VARCHAR(512) NOT NULL DEFAULT '' COMMENT '备注',

    `status` TINYINT(4) NOT NULL DEFAULT '1' COMMENT '状态 0-已过期 1-生效中 2-已取消 3-退款中 4-已退款',
    `dbctime` DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    `dbutime` DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',

    PRIMARY KEY (`id`),
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_transaction_id` (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户权益表';

# 问卷基本信息表
CREATE TABLE `questionnaire`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '问卷ID',
    `title`            VARCHAR(256) NOT NULL DEFAULT '' COMMENT '问卷标题',
    `subTitle`            VARCHAR(256) NOT NULL DEFAULT '' COMMENT '问卷副标题',
    `description`      TEXT         NOT NULL COMMENT '问卷描述',
    `questionnaireType` TINYINT(4)   NOT NULL DEFAULT '1' COMMENT '问卷类型 参考枚举 QuestionnaireTypeEnum',
    `time`            INT(11)   NOT NULL DEFAULT '10' COMMENT '预估作答时间(单位分)',
    `coverImage`      VARCHAR(512) NOT NULL DEFAULT '' COMMENT '封面图',
    `ordinal` INT(4)   NOT NULL DEFAULT '100' COMMENT '排序, 大的排前面',
    `status`           TINYINT(4)   NOT NULL DEFAULT '1' COMMENT '问卷状态 0:不可用 1:可用',
    `dbctime`          DATETIME(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '创建时间',
    `dbutime`          DATETIME(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '更新时间',
    PRIMARY KEY        (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问卷基本信息表';

# 问卷问题表
CREATE TABLE `questionnaire_question`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '问题ID',
    `questionnaireId` BIGINT(20) UNSIGNED NOT NULL COMMENT '问卷ID',
    `content`          VARCHAR(1024)         NOT NULL DEFAULT '' COMMENT '问题内容',
    `type`             TINYINT(4)   NOT NULL DEFAULT '1' COMMENT '问题类型 1:单选 2:多选 3:填空',
    `options`          VARCHAR(1024)      NOT NULL DEFAULT '[]'  COMMENT '问题选项(JSON格式，仅对单选和多选有效)',
    `required`         TINYINT(4)   NOT NULL DEFAULT '1' COMMENT '是否必答 0:非必答 1:必答',
    `ordinal`            INT(11)      NOT NULL DEFAULT '0' COMMENT '问题权重 大的排前面',
    `status`           TINYINT(4)   NOT NULL DEFAULT '1' COMMENT '状态 0:不可用 1:可用',
    `dbctime`          DATETIME(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '创建时间',
    `dbutime`          DATETIME(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '更新时间',
    PRIMARY KEY        (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='问卷问题表';

# 用户问卷结果
CREATE TABLE `user_questionnaire_result`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '记录ID',
    `userId`          BIGINT(20) UNSIGNED NOT NULL COMMENT '用户ID',
    `questionnaireId` BIGINT(20) UNSIGNED NOT NULL COMMENT '问卷ID',
    `questionId` BIGINT(20) UNSIGNED NOT NULL COMMENT '题目ID',
    `answers`          VARCHAR(1024)      NOT NULL DEFAULT '[]'  COMMENT '答案-多选时为答案的数组',

    `status`           TINYINT(4)   NOT NULL DEFAULT '1' COMMENT '状态 0:不可用 1:可用',
    `dbctime`          DATETIME(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '创建时间',
    `dbutime`          DATETIME(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '更新时间',
    PRIMARY KEY        (`id`),
    KEY `idx_userId_questionnaireId` (`userId`, `questionnaireId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户问卷得分表';

# 用户对话表
CREATE TABLE `chat`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '对话ID',
    `userId`          BIGINT(20) UNSIGNED NOT NULL COMMENT '用户ID',
    `model`             TINYINT(4)   NOT NULL DEFAULT '1' COMMENT '模型ID 1-DeepSeek',
    `title`            VARCHAR(256) NOT NULL DEFAULT '' COMMENT '对话标题',
    `prompt`        TEXT         NOT NULL COMMENT '提示词',

    `status`           TINYINT(4)   NOT NULL DEFAULT '1' COMMENT '状态 0:不可用 1:可用',
    `dbctime`          DATETIME(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '创建时间',
    `dbutime`          DATETIME(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '更新时间',
    PRIMARY KEY        (`id`),
    KEY idx_userId (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户对话表';

# 用户对话消息表
CREATE TABLE `chat_message`
(
    `id`               BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '对话ID',
    `userId`           BIGINT(20) UNSIGNED NOT NULL COMMENT '用户ID',
    `chatId`           BIGINT(20) UNSIGNED NOT NULL COMMENT '对话ID',
    `sessionId`           BIGINT(20) UNSIGNED NOT NULL COMMENT '轮次ID 同一轮次下ID一致',
    `role`             TINYINT(4)   NOT NULL DEFAULT '0' COMMENT '角色 1-模型 2-用户',
    `content`        TEXT         NOT NULL COMMENT '对话内容',

    `status`           TINYINT(4)   NOT NULL DEFAULT '1' COMMENT '状态 0:不可用 1:可用',
    `dbctime`          DATETIME(3) DEFAULT CURRENT_TIMESTAMP (3) COMMENT '创建时间',
    `dbutime`          DATETIME(3) DEFAULT CURRENT_TIMESTAMP (3) ON UPDATE CURRENT_TIMESTAMP (3) COMMENT '更新时间',
    PRIMARY KEY        (`id`),
    KEY idx_userId_chatId (`userId`, `chatId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户对话消息表';

