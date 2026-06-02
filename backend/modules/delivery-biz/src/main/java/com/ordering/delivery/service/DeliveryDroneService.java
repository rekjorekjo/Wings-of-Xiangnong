package com.ordering.delivery.service;

import com.ordering.delivery.controller.admin.delivery.vo.AdminDeliveryDroneRespVO;
import com.ordering.delivery.service.mock.DeliveryMockDataProvider;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryDroneService {

    @Resource
    private DeliveryMockDataProvider mockDataProvider;

    public List<AdminDeliveryDroneRespVO> getDroneList() {
        return mockDataProvider.getMockDrones();
    }
}
