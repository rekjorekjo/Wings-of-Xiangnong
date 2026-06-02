/**
 * Copyright (C) 2018-2022

 */
package com.ordering.module.order.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author hupeng
 * 支付相关枚举
 */
@Getter
@AllArgsConstructor
public enum PayTypeEnum {

	CASH("cash","现金支付"),
	ALI("alipay","支付宝支付"),
	WEIXIN("weixin","微信支付"),
	YUE("yue","余额支付"),
	INTEGRAL("integral","积分兑换");


	private String value;
	private String desc;

	public static PayTypeEnum toType(String value) {
		return Stream.of(PayTypeEnum.values())
				.filter(p -> p.value.equals(value))
				.findAny()
				.orElse(null);
	}


}
