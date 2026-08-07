CREATE TABLE IF NOT EXISTS `app_delivery_point` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '配送点ID',
  `code` varchar(64) NOT NULL COMMENT '配送点编码',
  `name` varchar(100) NOT NULL COMMENT '配送点名称',
  `address` varchar(255) NOT NULL DEFAULT '' COMMENT '配送点地址描述',
  `latitude` decimal(10,8) NOT NULL COMMENT '纬度，WGS-84',
  `longitude` decimal(11,8) NOT NULL COMMENT '经度，WGS-84',
  `flight_altitude` decimal(8,2) NOT NULL DEFAULT '10.00' COMMENT '默认巡航高度，米',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用',
  `verified` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否已通过无人机组验证',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注/安全说明',
  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_app_delivery_point_code` (`code`),
  KEY `idx_app_delivery_point_enabled` (`enabled`),
  KEY `idx_app_delivery_point_verified` (`verified`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='无人机配送点';

INSERT INTO `app_delivery_point`
  (`code`, `name`, `address`, `latitude`, `longitude`, `flight_altitude`, `enabled`, `verified`, `sort`, `remark`)
VALUES
  ('T_LIBRARY', '图书馆草坪降落点', '东南大学九龙湖校区李文正图书馆草坪', 31.88791480, 118.81327290, 10.00, 1, 1, 10, '无人机组样例 A_to_B 的目标点，已用于 Mission Planner 导入验证'),
  ('DORM_TEST_A', '宿舍区测试点A', '宿舍区附近候选配送点，待无人机组复核', 31.88695000, 118.81452000, 12.00, 1, 0, 20, '占位数据，不能直接用于真实飞行'),
  ('TEACHING_TEST_B', '教学区测试点B', '教学区附近候选配送点，待无人机组复核', 31.88905000, 118.81270000, 12.00, 1, 0, 30, '占位数据，不能直接用于真实飞行')
ON DUPLICATE KEY UPDATE
  `name` = VALUES(`name`),
  `address` = VALUES(`address`),
  `latitude` = VALUES(`latitude`),
  `longitude` = VALUES(`longitude`),
  `flight_altitude` = VALUES(`flight_altitude`),
  `enabled` = VALUES(`enabled`),
  `verified` = VALUES(`verified`),
  `sort` = VALUES(`sort`),
  `remark` = VALUES(`remark`);
