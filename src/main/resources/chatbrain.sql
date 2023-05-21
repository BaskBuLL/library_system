drop table if exists `user`;
CREATE TABLE `user`
(
    `id`                 bigint(32) unsigned NOT NULL COMMENT '自增主键',
    `name`               varchar(200)           DEFAULT '' COMMENT '名称',
    `email`              varchar(200)           DEFAULT '' COMMENT '邮箱',
    `head`               varchar(2000) NOT NULL DEFAULT '' COMMENT '头像',
    `password`           varchar(2000) NOT NULL COMMENT '密码',
    `verification_token` varchar(2000) NOT NULL DEFAULT '' COMMENT '头像',
    `status`             tinyint(1) NOT NULL DEFAULT '0' COMMENT '用户状态:0-生效,1-失效',
    `verified_status`    tinyint(1) NOT NULL DEFAULT '0' COMMENT '0-生效,1-失效',
    `create_time`        bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_time`        bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY                  `idx_email` (`email`) COMMENT '索引：email'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

drop table if exists `title`;
CREATE TABLE `title`
(
    `id`            bigint(32) unsigned NOT NULL COMMENT '自增主键',
    `title`         varchar(200)           DEFAULT '' COMMENT '标题',
    `desc`          varchar(200)           DEFAULT '' COMMENT '描述',
    `content`       text COMMENT '内容',
    `author_id`     bigint(32) NOT NULL COMMENT '作者id',
    `author_name`   varchar(200)  NOT NULL COMMENT '作者名称',
    `author_head`   varchar(2000) NOT NULL DEFAULT '' COMMENT '作者头像',
    `forward_count` bigint(32) unsigned NOT NULL COMMENT '转发次数',
    `chat_id`       bigint(32) unsigned NOT NULL DEFAULT '0' COMMENT '对话id',
    `like_count`    bigint(32) unsigned NOT NULL COMMENT '喜欢次数',
    `status`        tinyint(1) NOT NULL DEFAULT '0' COMMENT '文章状态:0-生效,1-失效',
    `create_time`   bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_time`   bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='文章表';

Drop table if exists `prompt`;
CREATE TABLE `prompt`
(
    `id`                bigint(32) unsigned NOT NULL COMMENT '自增主键',
    `author_id`         bigint(32) NOT NULL COMMENT '作者id',
    `content`           text COMMENT '内容',
    `parent_id`         bigint(32) unsigned NOT NULL DEFAULT '0' COMMENT '父prompt',
    `title_id`          bigint(32) unsigned NOT NULL DEFAULT '0' COMMENT '文章id',
    `prompt_run_count`  bigint(32) unsigned NOT NULL DEFAULT '0' COMMENT '运行次数',
    `prompt_fork_count` bigint(32) unsigned NOT NULL DEFAULT '0' COMMENT 'fork次数',
    `create_time`       bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_time`       bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='提示词表';

Drop table if exists `fork`;
CREATE TABLE `fork`
(
    `id`          bigint(32) unsigned NOT NULL COMMENT '自增主键',
    `user_id`     bigint(32) NOT NULL COMMENT '作者id',
    `prompt_id`   bigint(32) NOT NULL COMMENT '内容',
    `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='fork表';

Drop table if exists `prompt_context`;
CREATE TABLE `prompt_context`
(
    `id`          bigint(32) unsigned NOT NULL COMMENT '自增主键',
    `content`     text COMMENT '内容',
    `prompt_id`   bigint(32) unsigned NOT NULL COMMENT '提示id',
    `author_id`   bigint(32) NOT NULL COMMENT '作者id',
    `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='提示内容表';

Drop table if exists `chat`;
CREATE TABLE `chat`
(
    `id`          bigint(32) unsigned NOT NULL COMMENT '自增主键',
    `user_id`     bigint(32) NOT NULL COMMENT '用户id',
    `content`     text COMMENT '内容',
    `create_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='对话表';