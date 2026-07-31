package com.ordering.delivery.dal.mysql;

import com.ordering.delivery.dal.dataobject.DeliveryOrderDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeliveryOrderMapper {

    @Select("""
            SELECT
                id,
                order_id AS orderNo,
                IFNULL(shop_name, '') AS pickupSite,
                IFNULL(user_address, '') AS dropoffSite,
                paid,
                status,
                total_num AS totalNum,
                create_time AS createdAt
            FROM app_store_order
            WHERE deleted = 0
              AND IFNULL(is_system_del, 0) <> 1
              AND (order_type = 'takeout' OR shipping_type = 1)
              AND create_time >= CURDATE()
              AND create_time < DATE_ADD(CURDATE(), INTERVAL 1 DAY)
            ORDER BY create_time DESC
            LIMIT 200
            """)
    List<DeliveryOrderDO> selectRecentDeliveryOrders();
}
