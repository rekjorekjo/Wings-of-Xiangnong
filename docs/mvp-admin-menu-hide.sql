-- ============================================================
-- 香农之翼 MVP 阶段 - 管理端菜单隐藏 SQL
-- ============================================================
-- 执行前请务必备份 system_menu 表！
-- 备份命令：CREATE TABLE system_menu_backup_20260519 AS SELECT * FROM system_menu;
-- ============================================================

-- ============================================================
-- 一、确定隐藏的功能模块
-- ============================================================

-- 隐藏积分商城：MVP 阶段不做积分兑换/积分订单功能
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 2306
    OR parent_id = 2306
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 2306
      ) AS t
    )
  );

-- 隐藏快递管理：MVP 阶段使用无人机配送，不涉及快递物流
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 2243
    OR parent_id = 2243
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 2243
      ) AS t
    )
  );

-- 隐藏公众号管理：MVP 阶段暂不接入公众号
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 2084
    OR parent_id = 2084
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 2084
      ) AS t
    )
  );

-- ============================================================
-- 二、基础设施中隐藏的功能
-- ============================================================

-- 隐藏代码生成：MVP 阶段不需要代码生成器
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 115
    OR parent_id = 115
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 115
      ) AS t
    )
  );

-- 隐藏数据源配置：MVP 阶段使用单一数据源，不需要动态数据源配置
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 1255
    OR parent_id = 1255
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 1255
      ) AS t
    )
  );

-- 隐藏表单构建：MVP 阶段不需要表单构建工具
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 114
    OR parent_id = 114
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 114
      ) AS t
    )
  );

-- 隐藏 MySQL 监控：MVP 阶段暂不需要数据库监控
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 111
    OR parent_id = 111
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 111
      ) AS t
    )
  );

-- 隐藏 Redis 监控：MVP 阶段暂不需要缓存监控
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 113
    OR parent_id = 113
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 113
      ) AS t
    )
  );

-- 隐藏 Java 监控：MVP 阶段暂不需要应用监控
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 112
    OR parent_id = 112
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 112
      ) AS t
    )
  );

-- 隐藏定时任务：MVP 阶段暂不需要定时任务管理
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 110
    OR parent_id = 110
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 110
      ) AS t
    )
  );

-- 隐藏 API 日志：MVP 阶段暂不需要 API 日志管理
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 1083
    OR parent_id = 1083
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 1083
      ) AS t
    )
  );

-- ============================================================
-- 三、系统管理中隐藏的功能
-- ============================================================

-- 隐藏 OAuth 2.0：MVP 阶段暂不需要 OAuth2 授权管理
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 1261
    OR parent_id = 1261
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 1261
      ) AS t
    )
  );

-- 隐藏短信管理：MVP 阶段暂不需要短信功能
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 1093
    OR parent_id = 1093
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 1093
      ) AS t
    )
  );

-- 隐藏邮箱管理：MVP 阶段暂不需要邮件功能
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 2130
    OR parent_id = 2130
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 2130
      ) AS t
    )
  );

-- 隐藏审计日志：MVP 阶段暂不需要审计日志
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 108
    OR parent_id = 108
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 108
      ) AS t
    )
  );

-- ============================================================
-- 四、商城中心中隐藏的功能
-- ============================================================

-- 隐藏门店管理：MVP 阶段不做多门店运营，采用单店模式
-- 注意：shop/store 底层可能被商品、订单依赖，本阶段只隐藏菜单入口，不删除表和后端逻辑
UPDATE system_menu
SET visible = b'0'
WHERE deleted = b'0'
  AND (
    id = 2264
    OR parent_id = 2264
    OR parent_id IN (
      SELECT id FROM (
        SELECT id FROM system_menu WHERE parent_id = 2264
      ) AS t
    )
  );

-- ============================================================
-- 执行完成后请重新登录管理端验证
-- ============================================================
