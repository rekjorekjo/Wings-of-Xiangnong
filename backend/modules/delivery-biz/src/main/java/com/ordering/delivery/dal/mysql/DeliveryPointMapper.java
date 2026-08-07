package com.ordering.delivery.dal.mysql;

import com.ordering.delivery.dal.dataobject.DeliveryPointDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeliveryPointMapper {

    @Select("""
            SELECT
                id,
                code,
                name,
                address,
                latitude,
                longitude,
                flight_altitude AS flightAltitude,
                enabled,
                verified,
                sort,
                remark,
                create_time AS createdAt,
                update_time AS updatedAt
            FROM app_delivery_point
            WHERE deleted = 0
            ORDER BY sort ASC, id ASC
            """)
    List<DeliveryPointDO> selectPointList();
}
