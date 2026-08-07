package com.ordering.delivery.service;

import com.ordering.delivery.controller.admin.delivery.vo.AdminDeliveryPointRespVO;
import com.ordering.delivery.dal.dataobject.DeliveryPointDO;
import com.ordering.delivery.dal.mysql.DeliveryPointMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryPointService {

    private static final double EARTH_RADIUS_METERS = 6_378_137.0;

    @Resource
    private DeliveryPointMapper deliveryPointMapper;

    public List<AdminDeliveryPointRespVO> listPoints(BigDecimal userLatitude, BigDecimal userLongitude) {
        List<DeliveryPointDO> points = deliveryPointMapper.selectPointList();
        Optional<DeliveryPointDO> recommended = recommend(points, userLatitude, userLongitude);
        return points.stream()
                .map(point -> toResp(point, userLatitude, userLongitude, recommended))
                .sorted(pointComparator(userLatitude, userLongitude))
                .toList();
    }

    public AdminDeliveryPointRespVO recommendPoint(BigDecimal userLatitude, BigDecimal userLongitude) {
        validateLatitude("userLatitude", userLatitude);
        validateLongitude("userLongitude", userLongitude);
        DeliveryPointDO recommended = recommend(deliveryPointMapper.selectPointList(), userLatitude, userLongitude)
                .orElseThrow(() -> new IllegalStateException("No enabled delivery point available"));
        return toResp(recommended, userLatitude, userLongitude, Optional.of(recommended));
    }

    private Optional<DeliveryPointDO> recommend(List<DeliveryPointDO> points, BigDecimal userLatitude, BigDecimal userLongitude) {
        if (userLatitude == null || userLongitude == null) {
            return Optional.empty();
        }
        validateLatitude("userLatitude", userLatitude);
        validateLongitude("userLongitude", userLongitude);
        return points.stream()
                .filter(point -> Boolean.TRUE.equals(point.getEnabled()))
                .min(Comparator.comparingDouble(point -> distanceMeters(userLatitude, userLongitude, point.getLatitude(), point.getLongitude())));
    }

    private AdminDeliveryPointRespVO toResp(
            DeliveryPointDO point,
            BigDecimal userLatitude,
            BigDecimal userLongitude,
            Optional<DeliveryPointDO> recommended) {
        Double distance = userLatitude == null || userLongitude == null
                ? null
                : round(distanceMeters(userLatitude, userLongitude, point.getLatitude(), point.getLongitude()), 1);
        boolean isRecommended = recommended.map(value -> value.getCode().equals(point.getCode())).orElse(false);
        return new AdminDeliveryPointRespVO(
                point.getCode(),
                point.getName(),
                point.getAddress(),
                point.getLatitude(),
                point.getLongitude(),
                point.getFlightAltitude(),
                point.getEnabled(),
                point.getVerified(),
                distance,
                isRecommended);
    }

    private Comparator<AdminDeliveryPointRespVO> pointComparator(BigDecimal userLatitude, BigDecimal userLongitude) {
        if (userLatitude == null || userLongitude == null) {
            return Comparator.comparing(AdminDeliveryPointRespVO::code);
        }
        return Comparator
                .comparing((AdminDeliveryPointRespVO point) -> point.distanceMeters() == null ? Double.MAX_VALUE : point.distanceMeters())
                .thenComparing(AdminDeliveryPointRespVO::code);
    }

    private double distanceMeters(BigDecimal lat1, BigDecimal lng1, BigDecimal lat2, BigDecimal lng2) {
        double radLat1 = Math.toRadians(lat1.doubleValue());
        double radLat2 = Math.toRadians(lat2.doubleValue());
        double deltaLat = radLat1 - radLat2;
        double deltaLng = Math.toRadians(lng1.doubleValue()) - Math.toRadians(lng2.doubleValue());
        double a = Math.pow(Math.sin(deltaLat / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(deltaLng / 2), 2);
        return 2 * Math.asin(Math.sqrt(a)) * EARTH_RADIUS_METERS;
    }

    private double round(double value, int scale) {
        double factor = Math.pow(10, scale);
        return Math.round(value * factor) / factor;
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
}
