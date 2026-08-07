package com.ordering.delivery.dal.dataobject;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DeliveryPointDO {

    private Long id;

    private String code;

    private String name;

    private String address;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private BigDecimal flightAltitude;

    private Boolean enabled;

    private Boolean verified;

    private Integer sort;

    private String remark;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
