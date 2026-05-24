package co.yixiang.yshop.module.delivery.service;

import co.yixiang.yshop.module.delivery.controller.admin.delivery.vo.AdminDeliveryDroneRespVO;
import co.yixiang.yshop.module.delivery.service.mock.DeliveryMockDataProvider;
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
