package co.yixiang.yshop.module.delivery.service;

import co.yixiang.yshop.module.delivery.controller.admin.delivery.vo.AdminDeliveryDroneRespVO;
import co.yixiang.yshop.module.delivery.controller.admin.delivery.vo.AdminDeliveryTaskRespVO;
import co.yixiang.yshop.module.delivery.controller.app.delivery.vo.AppOrderDeliveryRespVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class DeliveryMockService {

    @Value("${xiangnong.delivery.mock-enabled:true}")
    private boolean mockEnabled;

    public List<AdminDeliveryTaskRespVO> getTasks(String orderNo, String status, String droneNo) {
        if (!mockEnabled) {
            return List.of();
        }
        return mockTasks().stream()
                .filter(task -> matches(orderNo, task.orderNo()))
                .filter(task -> matches(status, task.status()))
                .filter(task -> matches(droneNo, task.droneNo()))
                .toList();
    }

    public List<AdminDeliveryDroneRespVO> getDrones() {
        if (!mockEnabled) {
            return List.of();
        }
        return List.of(
                new AdminDeliveryDroneRespVO(1L, "XN-D001", "idle", "空闲", 92, "Station A", "无", LocalDateTime.now().minusMinutes(3)),
                new AdminDeliveryDroneRespVO(2L, "XN-D002", "flying", "配送中", 68, "飞越图书馆上空", "2杯拿铁", LocalDateTime.now().minusMinutes(1)),
                new AdminDeliveryDroneRespVO(3L, "XN-D003", "charging", "充电中", 34, "Station A", "无", LocalDateTime.now().minusMinutes(8))
        );
    }

    public Optional<AppOrderDeliveryRespVO> getOrderDelivery(Long orderId) {
        if (!mockEnabled) {
            return Optional.empty();
        }
        return mockAppDeliveries().stream()
                .filter(d -> d.orderId().equals(orderId))
                .findFirst()
                .or(() -> Optional.of(new AppOrderDeliveryRespVO(
                        orderId,
                        900000L + orderId,
                        "pending",
                        "待分配",
                        LocalDateTime.now().plusMinutes(18),
                        "待分配无人机",
                        List.of(new AppOrderDeliveryRespVO.ProgressVO("pending", "订单已提交，等待分配无人机", LocalDateTime.now().minusMinutes(1)))
                )));
    }

    private List<AdminDeliveryTaskRespVO> mockTasks() {
        return List.of(
                new AdminDeliveryTaskRespVO(
                        10001L,
                        "XN202605230001",
                        "Xiangnong Station A",
                        "Mingde Building pickup point",
                        null,
                        "pending",
                        "待分配",
                        0,
                        16,
                        LocalDateTime.now().minusMinutes(6),
                        List.of(new AdminDeliveryTaskRespVO.TimelineVO("pending", "订单已进入配送池", LocalDateTime.now().minusMinutes(6)))
                ),
                new AdminDeliveryTaskRespVO(
                        10002L,
                        "XN202605230002",
                        "Xiangnong Station A",
                        "Student Apartment 7",
                        "XN-D002",
                        "flying",
                        "配送中",
                        65,
                        9,
                        LocalDateTime.now().minusMinutes(14),
                        List.of(
                                new AdminDeliveryTaskRespVO.TimelineVO("pending", "订单已创建", LocalDateTime.now().minusMinutes(14)),
                                new AdminDeliveryTaskRespVO.TimelineVO("assigned", "无人机 XN-D002 已分配", LocalDateTime.now().minusMinutes(10)),
                                new AdminDeliveryTaskRespVO.TimelineVO("flying", "无人机已起飞", LocalDateTime.now().minusMinutes(5))
                        )
                ),
                new AdminDeliveryTaskRespVO(
                        10003L,
                        "XN202605230003",
                        "Xiangnong Station A",
                        "Library south gate",
                        "XN-D001",
                        "arrived",
                        "已到达",
                        90,
                        0,
                        LocalDateTime.now().minusMinutes(26),
                        List.of(
                                new AdminDeliveryTaskRespVO.TimelineVO("pending", "订单已创建", LocalDateTime.now().minusMinutes(26)),
                                new AdminDeliveryTaskRespVO.TimelineVO("assigned", "无人机 XN-D001 已分配", LocalDateTime.now().minusMinutes(23)),
                                new AdminDeliveryTaskRespVO.TimelineVO("arrived", "无人机已到达取货点", LocalDateTime.now().minusMinutes(2))
                        )
                )
        );
    }

    private List<AppOrderDeliveryRespVO> mockAppDeliveries() {
        return List.of(
                new AppOrderDeliveryRespVO(
                        30001L,
                        10001L,
                        "pending",
                        "待分配",
                        LocalDateTime.now().plusMinutes(16),
                        "待分配无人机",
                        List.of(new AppOrderDeliveryRespVO.ProgressVO("pending", "订单已提交，等待分配无人机", LocalDateTime.now().minusMinutes(6)))
                ),
                new AppOrderDeliveryRespVO(
                        30002L,
                        10002L,
                        "flying",
                        "配送中",
                        LocalDateTime.now().plusMinutes(9),
                        "飞越图书馆上空",
                        List.of(
                                new AppOrderDeliveryRespVO.ProgressVO("pending", "订单已创建", LocalDateTime.now().minusMinutes(14)),
                                new AppOrderDeliveryRespVO.ProgressVO("assigned", "无人机已分配", LocalDateTime.now().minusMinutes(10)),
                                new AppOrderDeliveryRespVO.ProgressVO("flying", "无人机已起飞", LocalDateTime.now().minusMinutes(5))
                        )
                ),
                new AppOrderDeliveryRespVO(
                        30003L,
                        10003L,
                        "arrived",
                        "已到达",
                        LocalDateTime.now().minusMinutes(2),
                        "图书馆南门",
                        List.of(
                                new AppOrderDeliveryRespVO.ProgressVO("pending", "订单已创建", LocalDateTime.now().minusMinutes(26)),
                                new AppOrderDeliveryRespVO.ProgressVO("assigned", "无人机已分配", LocalDateTime.now().minusMinutes(23)),
                                new AppOrderDeliveryRespVO.ProgressVO("arrived", "无人机已到达", LocalDateTime.now().minusMinutes(2))
                        )
                )
        );
    }

    private boolean matches(String expected, String actual) {
        return expected == null || expected.isBlank()
                || (actual != null && actual.toLowerCase(Locale.ROOT).contains(expected.toLowerCase(Locale.ROOT)));
    }
}
