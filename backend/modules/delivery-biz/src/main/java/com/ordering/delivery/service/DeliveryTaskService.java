package com.ordering.delivery.service;

import com.ordering.delivery.controller.admin.delivery.vo.AdminDeliveryTaskRespVO;
import com.ordering.delivery.controller.app.delivery.vo.AppOrderDeliveryRespVO;
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

    public List<AdminDeliveryTaskRespVO> getTaskList(String orderNo, String status, String droneNo) {
        return mockDataProvider.getMockTasks().stream()
                .filter(task -> matches(orderNo, task.orderNo()))
                .filter(task -> matches(status, task.status()))
                .filter(task -> matches(droneNo, task.droneNo()))
                .toList();
    }

    public Optional<AppOrderDeliveryRespVO> getOrderDelivery(Long orderId) {
        return mockDataProvider.findMockAppDeliveryByOrderId(orderId)
                .or(() -> Optional.of(mockDataProvider.createDefaultAppDelivery(orderId)));
    }

    private boolean matches(String expected, String actual) {
        return expected == null || expected.isBlank()
                || (actual != null && actual.toLowerCase(Locale.ROOT).contains(expected.toLowerCase(Locale.ROOT)));
    }
}
