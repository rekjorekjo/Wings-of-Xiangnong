/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.wings.co

 */
package com.wings.module.order.service.storeorder.dto;


import lombok.Data;

import java.util.List;

@Data
public class OrderCountDto {

    private List<String> column;

    private List<OrderCountData> orderCountDatas;

    @Data
    public static class OrderCountData{
        private String name;

        private Integer value;
    }
}
