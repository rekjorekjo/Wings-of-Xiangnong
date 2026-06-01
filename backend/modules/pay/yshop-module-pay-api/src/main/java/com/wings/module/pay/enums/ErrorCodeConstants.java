package com.wings.module.pay.enums;

import com.wings.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    ErrorCode MERCHANT_DETAILS_NOT_EXISTS = new ErrorCode(1008009000, "支付服务商配置不存在");
}

