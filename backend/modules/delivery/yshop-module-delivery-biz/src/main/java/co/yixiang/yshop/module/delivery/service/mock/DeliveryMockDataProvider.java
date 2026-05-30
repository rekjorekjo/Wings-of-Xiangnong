package co.yixiang.yshop.module.delivery.service.mock;

import co.yixiang.yshop.module.delivery.controller.admin.delivery.vo.AdminDeliveryDroneRespVO;
import co.yixiang.yshop.module.delivery.controller.admin.delivery.vo.AdminDeliveryTaskRespVO;
import co.yixiang.yshop.module.delivery.controller.app.delivery.vo.AppOrderDeliveryRespVO;
import co.yixiang.yshop.module.delivery.framework.config.DeliveryProperties;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class DeliveryMockDataProvider {

    @Resource
    private DeliveryProperties deliveryProperties;

    public boolean isMockEnabled() {
        return deliveryProperties.getMockEnabled();
    }

    public List<AdminDeliveryTaskRespVO> getMockTasks() {
        if (!deliveryProperties.getMockEnabled()) {
            return List.of();
        }
        LocalDateTime now = LocalDateTime.now();
        String siteName = deliveryProperties.getDefaultSiteName();
        return List.of(
                new AdminDeliveryTaskRespVO(
                        10001L,
                        "XN202605230001",
                        siteName,
                        "Mingde Building pickup point",
                        null,
                        "pending",
                        "待分配",
                        0,
                        16,
                        now.minusMinutes(6),
                        List.of(new AdminDeliveryTaskRespVO.TimelineVO("pending", "订单已进入配送池", now.minusMinutes(6)))
                ),
                new AdminDeliveryTaskRespVO(
                        10002L,
                        "XN202605230002",
                        siteName,
                        "Student Apartment 7",
                        "XN-D002",
                        "flying",
                        "配送中",
                        65,
                        9,
                        now.minusMinutes(14),
                        List.of(
                                new AdminDeliveryTaskRespVO.TimelineVO("pending", "订单已创建", now.minusMinutes(14)),
                                new AdminDeliveryTaskRespVO.TimelineVO("assigned", "无人机 XN-D002 已分配", now.minusMinutes(10)),
                                new AdminDeliveryTaskRespVO.TimelineVO("flying", "无人机已起飞", now.minusMinutes(5))
                        )
                ),
                new AdminDeliveryTaskRespVO(
                        10003L,
                        "XN202605230003",
                        siteName,
                        "Library south gate",
                        "XN-D001",
                        "arrived",
                        "已到达",
                        90,
                        0,
                        now.minusMinutes(26),
                        List.of(
                                new AdminDeliveryTaskRespVO.TimelineVO("pending", "订单已创建", now.minusMinutes(26)),
                                new AdminDeliveryTaskRespVO.TimelineVO("assigned", "无人机 XN-D001 已分配", now.minusMinutes(23)),
                                new AdminDeliveryTaskRespVO.TimelineVO("arrived", "无人机已到达取货点", now.minusMinutes(2))
                        )
                )
        );
    }

    public List<AdminDeliveryDroneRespVO> getMockDrones() {
        if (!deliveryProperties.getMockEnabled()) {
            return List.of();
        }
        LocalDateTime now = LocalDateTime.now();
        return List.of(
                new AdminDeliveryDroneRespVO(1L, "XN-D001", "idle", "空闲", 92, "Station A", "无", now.minusMinutes(3)),
                new AdminDeliveryDroneRespVO(2L, "XN-D002", "flying", "配送中", 68, "飞越图书馆上空", "2杯拿铁", now.minusMinutes(1)),
                new AdminDeliveryDroneRespVO(3L, "XN-D003", "charging", "充电中", 34, "Station A", "无", now.minusMinutes(8))
        );
    }

    public List<AppOrderDeliveryRespVO> getMockAppDeliveries() {
        if (!deliveryProperties.getMockEnabled()) {
            return List.of();
        }
        LocalDateTime now = LocalDateTime.now();
        return List.of(
                new AppOrderDeliveryRespVO(
                        30001L,
                        10001L,
                        "pending",
                        "待分配",
                        now.plusMinutes(16),
                        "待分配无人机",
                        List.of(new AppOrderDeliveryRespVO.ProgressVO("pending", "订单已提交，等待分配无人机", now.minusMinutes(6)))
                ),
                new AppOrderDeliveryRespVO(
                        30002L,
                        10002L,
                        "flying",
                        "配送中",
                        now.plusMinutes(9),
                        "飞越图书馆上空",
                        List.of(
                                new AppOrderDeliveryRespVO.ProgressVO("pending", "订单已创建", now.minusMinutes(14)),
                                new AppOrderDeliveryRespVO.ProgressVO("assigned", "无人机已分配", now.minusMinutes(10)),
                                new AppOrderDeliveryRespVO.ProgressVO("flying", "无人机已起飞", now.minusMinutes(5))
                        )
                ),
                new AppOrderDeliveryRespVO(
                        30003L,
                        10003L,
                        "arrived",
                        "已到达",
                        now.minusMinutes(2),
                        "图书馆南门",
                        List.of(
                                new AppOrderDeliveryRespVO.ProgressVO("pending", "订单已创建", now.minusMinutes(26)),
                                new AppOrderDeliveryRespVO.ProgressVO("assigned", "无人机已分配", now.minusMinutes(23)),
                                new AppOrderDeliveryRespVO.ProgressVO("arrived", "无人机已到达", now.minusMinutes(2))
                        )
                )
        );
    }

    public Optional<AppOrderDeliveryRespVO> findMockAppDeliveryByOrderId(Long orderId) {
        return getMockAppDeliveries().stream()
                .filter(d -> d.orderId().equals(orderId))
                .findFirst();
    }

    public AppOrderDeliveryRespVO createDefaultAppDelivery(Long orderId) {
        LocalDateTime now = LocalDateTime.now();
        return new AppOrderDeliveryRespVO(
                orderId,
                900000L + orderId,
                "pending",
                "待分配",
                now.plusMinutes(18),
                "待分配无人机",
                List.of(new AppOrderDeliveryRespVO.ProgressVO("pending", "订单已提交，等待分配无人机", now.minusMinutes(1)))
        );
    }
}
