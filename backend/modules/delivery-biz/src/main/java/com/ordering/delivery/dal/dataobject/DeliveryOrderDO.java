package com.ordering.delivery.dal.dataobject;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryOrderDO {

    private Long id;

    private String orderNo;

    private String pickupSite;

    private String dropoffSite;

    private Integer paid;

    private Integer status;

    private Integer totalNum;

    private LocalDateTime createdAt;
}
