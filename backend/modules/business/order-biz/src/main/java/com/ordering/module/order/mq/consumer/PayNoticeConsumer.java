package com.ordering.module.order.mq.consumer;

import com.ordering.framework.mq.redis.core.stream.AbstractRedisStreamMessageListener;
import com.ordering.module.order.service.storeorder.AppStoreOrderService;
import com.ordering.module.pay.mq.message.PayNoticeMessage;
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
