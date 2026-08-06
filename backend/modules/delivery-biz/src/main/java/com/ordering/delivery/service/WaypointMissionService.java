package com.ordering.delivery.service;

import com.ordering.delivery.dal.dataobject.DeliveryOrderDO;
import com.ordering.delivery.dal.mysql.DeliveryOrderMapper;
import com.ordering.delivery.framework.config.DeliveryProperties;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Service
public class WaypointMissionService {

    private static final int MAV_FRAME_GLOBAL = 0;
    private static final int MAV_FRAME_GLOBAL_RELATIVE_ALT = 3;
    private static final int MAV_CMD_NAV_WAYPOINT = 16;
    private static final int MAV_CMD_NAV_LAND = 21;
    private static final int MAV_CMD_NAV_TAKEOFF = 22;

    @Resource
    private DeliveryProperties deliveryProperties;

    @Resource
    private DeliveryOrderMapper deliveryOrderMapper;

    public WaypointMissionFile generateMission(
            Long taskId,
            BigDecimal destinationLatitude,
            BigDecimal destinationLongitude,
            BigDecimal startLatitude,
            BigDecimal startLongitude,
            BigDecimal homeAltitude,
            BigDecimal flightAltitude) {
        DeliveryOrderDO order = deliveryOrderMapper.selectDeliveryOrderById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Delivery task not found: " + taskId));

        BigDecimal resolvedStartLatitude = fallback(startLatitude, deliveryProperties.getDefaultLatitude());
        BigDecimal resolvedStartLongitude = fallback(startLongitude, deliveryProperties.getDefaultLongitude());
        BigDecimal resolvedHomeAltitude = fallback(homeAltitude, deliveryProperties.getDefaultHomeAltitude());
        BigDecimal resolvedFlightAltitude = fallback(flightAltitude, deliveryProperties.getDefaultFlightAltitude());

        validateLatitude("startLatitude", resolvedStartLatitude);
        validateLongitude("startLongitude", resolvedStartLongitude);
        validateLatitude("destinationLatitude", destinationLatitude);
        validateLongitude("destinationLongitude", destinationLongitude);
        validateAltitude("homeAltitude", resolvedHomeAltitude, false);
        validateAltitude("flightAltitude", resolvedFlightAltitude, true);

        String content = renderMission(
                resolvedStartLatitude.doubleValue(),
                resolvedStartLongitude.doubleValue(),
                destinationLatitude.doubleValue(),
                destinationLongitude.doubleValue(),
                resolvedHomeAltitude.doubleValue(),
                resolvedFlightAltitude.doubleValue());

        String orderNo = order.getOrderNo() == null || order.getOrderNo().isBlank()
                ? String.valueOf(taskId)
                : order.getOrderNo();
        return new WaypointMissionFile(orderNo + ".waypoints", content.getBytes(StandardCharsets.UTF_8));
    }

    private String renderMission(
            double startLatitude,
            double startLongitude,
            double destinationLatitude,
            double destinationLongitude,
            double homeAltitude,
            double flightAltitude) {
        return "QGC WPL 110\n"
                + renderLine(0, 1, MAV_FRAME_GLOBAL, MAV_CMD_NAV_WAYPOINT, startLatitude, startLongitude, homeAltitude)
                + renderLine(1, 0, MAV_FRAME_GLOBAL_RELATIVE_ALT, MAV_CMD_NAV_TAKEOFF, 0.0, 0.0, flightAltitude)
                + renderLine(2, 0, MAV_FRAME_GLOBAL_RELATIVE_ALT, MAV_CMD_NAV_WAYPOINT, destinationLatitude, destinationLongitude, flightAltitude)
                + renderLine(3, 0, MAV_FRAME_GLOBAL_RELATIVE_ALT, MAV_CMD_NAV_LAND, destinationLatitude, destinationLongitude, 0.0);
    }

    private String renderLine(int sequence, int current, int frame, int command, double latitude, double longitude, double altitude) {
        return String.format(
                Locale.US,
                "%d\t%d\t%d\t%d\t0.00000000\t0.00000000\t0.00000000\t0.00000000\t%.8f\t%.8f\t%.6f\t1%n",
                sequence,
                current,
                frame,
                command,
                latitude,
                longitude,
                altitude);
    }

    private BigDecimal fallback(BigDecimal value, BigDecimal fallback) {
        return value == null ? fallback : value;
    }

    private void validateLatitude(String field, BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.valueOf(-90)) < 0 || value.compareTo(BigDecimal.valueOf(90)) > 0) {
            throw new IllegalArgumentException(field + " must be between -90 and 90");
        }
    }

    private void validateLongitude(String field, BigDecimal value) {
        if (value == null || value.compareTo(BigDecimal.valueOf(-180)) < 0 || value.compareTo(BigDecimal.valueOf(180)) > 0) {
            throw new IllegalArgumentException(field + " must be between -180 and 180");
        }
    }

    private void validateAltitude(String field, BigDecimal value, boolean positive) {
        if (value == null || value.compareTo(BigDecimal.valueOf(-500)) < 0 || value.compareTo(BigDecimal.valueOf(10_000)) > 0) {
            throw new IllegalArgumentException(field + " must be between -500 and 10000");
        }
        if (positive && value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(field + " must be greater than 0");
        }
    }

    public record WaypointMissionFile(String fileName, byte[] content) {

        public String asText() {
            return new String(content, StandardCharsets.UTF_8);
        }
    }
}
