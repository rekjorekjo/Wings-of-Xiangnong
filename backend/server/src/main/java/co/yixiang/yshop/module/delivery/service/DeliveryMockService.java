package co.yixiang.yshop.module.delivery.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
public class DeliveryMockService {

    @Value("${xiangnong.delivery.mock-enabled:true}")
    private boolean mockEnabled;

    public List<DeliveryTaskRespVO> getTasks(String status, String droneNo) {
        if (!mockEnabled) {
            return List.of();
        }
        return mockTasks().stream()
                .filter(task -> matches(status, task.status()))
                .filter(task -> matches(droneNo, task.droneNo()))
                .toList();
    }

    public List<DeliveryDroneRespVO> getDrones() {
        if (!mockEnabled) {
            return List.of();
        }
        return List.of(
                new DeliveryDroneRespVO("XN-D001", "idle", 92, "A1", "Idle", LocalDateTime.now().minusMinutes(3)),
                new DeliveryDroneRespVO("XN-D002", "delivering", 68, "B2", "Delivering", LocalDateTime.now().minusMinutes(1)),
                new DeliveryDroneRespVO("XN-D003", "charging", 34, "C1", "Charging", LocalDateTime.now().minusMinutes(8))
        );
    }

    public Optional<DeliveryTaskRespVO> getOrderDelivery(Long orderId) {
        if (!mockEnabled) {
            return Optional.empty();
        }
        return mockTasks().stream()
                .filter(task -> Objects.equals(task.orderId(), orderId))
                .findFirst()
                .or(() -> Optional.of(new DeliveryTaskRespVO(
                        900000L + orderId,
                        orderId,
                        "XN-MOCK-" + orderId,
                        "pending",
                        "Pending dispatch",
                        null,
                        "Default station",
                        "Customer point",
                        LocalDateTime.now().plusMinutes(18),
                        List.of(new DeliveryTrackRespVO("created", "Delivery task created", LocalDateTime.now().minusMinutes(1)))
                )));
    }

    private List<DeliveryTaskRespVO> mockTasks() {
        return List.of(
                new DeliveryTaskRespVO(
                        10001L,
                        30001L,
                        "XN202605230001",
                        "pending",
                        "Pending dispatch",
                        null,
                        "Xiangnong Station A",
                        "Mingde Building pickup point",
                        LocalDateTime.now().plusMinutes(16),
                        List.of(new DeliveryTrackRespVO("created", "Order entered delivery pool", LocalDateTime.now().minusMinutes(6)))
                ),
                new DeliveryTaskRespVO(
                        10002L,
                        30002L,
                        "XN202605230002",
                        "delivering",
                        "Delivering",
                        "XN-D002",
                        "Xiangnong Station A",
                        "Student Apartment 7",
                        LocalDateTime.now().plusMinutes(9),
                        List.of(
                                new DeliveryTrackRespVO("created", "Delivery task created", LocalDateTime.now().minusMinutes(14)),
                                new DeliveryTrackRespVO("assigned", "Drone XN-D002 assigned", LocalDateTime.now().minusMinutes(10)),
                                new DeliveryTrackRespVO("departed", "Drone departed", LocalDateTime.now().minusMinutes(5))
                        )
                ),
                new DeliveryTaskRespVO(
                        10003L,
                        30003L,
                        "XN202605230003",
                        "arrived",
                        "Arrived",
                        "XN-D001",
                        "Xiangnong Station A",
                        "Library south gate",
                        LocalDateTime.now().minusMinutes(2),
                        List.of(
                                new DeliveryTrackRespVO("created", "Delivery task created", LocalDateTime.now().minusMinutes(26)),
                                new DeliveryTrackRespVO("assigned", "Drone XN-D001 assigned", LocalDateTime.now().minusMinutes(23)),
                                new DeliveryTrackRespVO("arrived", "Drone arrived at customer point", LocalDateTime.now().minusMinutes(2))
                        )
                )
        );
    }

    private boolean matches(String expected, String actual) {
        return expected == null || expected.isBlank()
                || (actual != null && actual.toLowerCase(Locale.ROOT).contains(expected.toLowerCase(Locale.ROOT)));
    }

    public record DeliveryTaskRespVO(
            Long id,
            Long orderId,
            String taskNo,
            String status,
            String statusText,
            String droneNo,
            String fromSite,
            String toSite,
            LocalDateTime estimatedArrivalTime,
            List<DeliveryTrackRespVO> tracks
    ) {
    }

    public record DeliveryDroneRespVO(
            String droneNo,
            String status,
            Integer battery,
            String currentSite,
            String statusText,
            LocalDateTime updatedTime
    ) {
    }

    public record DeliveryTrackRespVO(
            String node,
            String content,
            LocalDateTime time
    ) {
    }
}
