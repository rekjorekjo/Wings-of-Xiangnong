package com.ordering.delivery.service;

import com.ordering.delivery.controller.admin.delivery.vo.AdminDeliveryTaskRespVO;
import com.ordering.delivery.controller.app.delivery.vo.AppOrderDeliveryRespVO;
import com.ordering.delivery.dal.dataobject.DeliveryOrderDO;
import com.ordering.delivery.dal.mysql.DeliveryOrderMapper;
import com.ordering.delivery.service.mock.DeliveryMockDataProvider;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class DeliveryTaskService {

    @Resource
    private DeliveryMockDataProvider mockDataProvider;

    @Resource
    private DeliveryOrderMapper deliveryOrderMapper;

    public List<AdminDeliveryTaskRespVO> getTaskList(String orderNo, String status, String droneNo) {
        return deliveryOrderMapper.selectRecentDeliveryOrders().stream()
                .map(this::toDeliveryTask)
                .filter(task -> matches(orderNo, task.orderNo()))
                .filter(task -> matches(status, task.status()))
                .filter(task -> matches(droneNo, task.droneNo()))
                .toList();
    }

    public Optional<AppOrderDeliveryRespVO> getOrderDelivery(Long orderId) {
        return mockDataProvider.findMockAppDeliveryByOrderId(orderId)
                .or(() -> Optional.of(mockDataProvider.createDefaultAppDelivery(orderId)));
    }

    private AdminDeliveryTaskRespVO toDeliveryTask(DeliveryOrderDO order) {
        TaskStatusView statusView = resolveStatus(order);
        return new AdminDeliveryTaskRespVO(
                order.getId(),
                order.getOrderNo(),
                fallback(order.getPickupSite(), "门店"),
                fallback(order.getDropoffSite(), "收货地址未填写"),
                "",
                statusView.status(),
                statusView.text(),
                statusView.progress(),
                statusView.etaMinutes(),
                order.getCreatedAt(),
                List.of(new AdminDeliveryTaskRespVO.TimelineVO(statusView.status(), statusView.timelineTitle(), order.getCreatedAt()))
        );
    }

    private TaskStatusView resolveStatus(DeliveryOrderDO order) {
        if (order.getPaid() == null || order.getPaid() == 0) {
            return new TaskStatusView("pending", "待支付", 0, 0, "订单已创建，等待支付");
        }
        if (order.getStatus() != null && order.getStatus() >= 2) {
            return new TaskStatusView("completed", "已完成", 100, 0, "订单已完成");
        }
        if (order.getStatus() != null && order.getStatus() == 1) {
            return new TaskStatusView("flying", "配送中", 60, 10, "订单已发出，配送中");
        }
        return new TaskStatusView("pending", "待配送", 0, 15, "订单已支付，等待配送");
    }

    private String fallback(String value, String fallback) {
        return value == null || value.isBlank() ? fallback : value;
    }

    private boolean matches(String expected, String actual) {
        return expected == null || expected.isBlank()
                || (actual != null && actual.toLowerCase(Locale.ROOT).contains(expected.toLowerCase(Locale.ROOT)));
    }

    private record TaskStatusView(String status, String text, Integer progress, Integer etaMinutes, String timelineTitle) {
    }
}
