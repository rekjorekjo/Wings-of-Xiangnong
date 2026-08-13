package com.ordering.delivery.dal.mysql;

import com.ordering.delivery.dal.dataobject.DeliveryPointDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Insert("""
            INSERT INTO app_delivery_point (
                code,
                name,
                address,
                latitude,
                longitude,
                flight_altitude,
                enabled,
                verified,
                sort,
                remark
            ) VALUES (
                #{code},
                #{name},
                #{address},
                #{latitude},
                #{longitude},
                #{flightAltitude},
                #{enabled},
                #{verified},
                #{sort},
                #{remark}
            )
            """)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertPoint(DeliveryPointDO point);

    @Update("""
            UPDATE app_delivery_point
            SET
                code = #{code},
                name = #{name},
                address = #{address},
                latitude = #{latitude},
                longitude = #{longitude},
                flight_altitude = #{flightAltitude},
                enabled = #{enabled},
                verified = #{verified},
                sort = #{sort},
                remark = #{remark}
            WHERE id = #{id}
              AND deleted = 0
            """)
    int updatePoint(DeliveryPointDO point);

    @Update("""
            UPDATE app_delivery_point
            SET deleted = 1
            WHERE id = #{id}
              AND deleted = 0
            """)
    int deletePoint(Long id);
}
