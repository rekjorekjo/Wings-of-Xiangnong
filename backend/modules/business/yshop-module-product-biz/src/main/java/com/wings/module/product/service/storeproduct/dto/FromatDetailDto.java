/**
 * Copyright (C) 2018-2022
 * All rights reserved, Designed By www.wings.co

 */
package com.wings.module.product.service.storeproduct.dto;

import lombok.*;

import java.util.List;

/**
 * @ClassName FromatDetailDTO
 * @Author hupeng <610796224@qq.com>
 * @Date 2019/10/12
 **/

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FromatDetailDto {
    private  String attrHidden;

    private  String detailValue;

    private List<String> detail;

    private String value;

}
