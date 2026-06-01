package com.wings.module.order.mq.consumer;

import com.wings.framework.mq.redis.core.stream.AbstractRedisStreamMessageListener;
import com.wings.module.order.service.storeorder.AppStoreOrderService;
import com.wings.module.pay.mq.message.PayNoticeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 消息队列处理支付消息
 */
@Component
@Slf4j
public class PayNoticeConsumer extends AbstractRedisStreamMessageListener<PayNoticeMessage> {

    @Resource
    private AppStoreOrderService appStoreOrderService;

    @Override
    public void onMessage(PayNoticeMessage message) {
        log.info("[onMessage][支付消息内容({})]", message);
        appStoreOrderService.paySuccess(message.getOrderId(),message.getPayType());

    }
}
