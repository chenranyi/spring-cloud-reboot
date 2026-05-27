-- 订单数据库
CREATE DATABASE IF NOT EXISTS order_db;
USE order_db;

CREATE TABLE IF NOT EXISTS `order` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `user_id` bigint NOT NULL,
    `product_id` bigint NOT NULL,
    `quantity` int NOT NULL,
    `amount` decimal(10,2) NOT NULL,
    `status` tinyint DEFAULT 0 COMMENT '0-待支付 1-已支付 2-已取消',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 库存数据库
CREATE DATABASE IF NOT EXISTS stock_db;
USE stock_db;

CREATE TABLE IF NOT EXISTS `stock` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `product_id` bigint NOT NULL,
    `total` int NOT NULL COMMENT '总库存',
    `used` int DEFAULT 0 COMMENT '已使用',
    `available` int DEFAULT 0 COMMENT '可用库存',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_product_id` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `stock` (`product_id`, `total`, `used`, `available`) VALUES (100, 100, 0, 100);

-- 积分数据库
CREATE DATABASE IF NOT EXISTS point_db;
USE point_db;

CREATE TABLE IF NOT EXISTS `point` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `user_id` bigint NOT NULL,
    `total` int DEFAULT 0 COMMENT '总积分',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `point` (`user_id`, `total`) VALUES (1, 1000);

-- Seata undo_log（每个业务库都需要）
CREATE TABLE IF NOT EXISTS `undo_log` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `branch_id` bigint NOT NULL,
    `xid` varchar(100) NOT NULL,
    `context` varchar(128) NOT NULL,
    `rollback_info` longblob NOT NULL,
    `log_status` int NOT NULL,
    `log_created` datetime NOT NULL,
    `log_modified` datetime NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
