package com.ordering.delivery.service;

import com.ordering.delivery.controller.admin.delivery.vo.AdminDeliveryPointRespVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryPointService {

    private static final double EARTH_RADIUS_METERS = 6_378_137.0;

    private final List<DeliveryPoint> mockPoints = List.of(
            new DeliveryPoint(
                    "T_LIBRARY",
                    "图书馆草坪降落点",
                    "东南大学九龙湖校区李文正图书馆草坪",
                    new BigDecimal("31.88791480"),
                    new BigDecimal("118.81327290"),
                    new BigDecimal("10.0"),
                    true,
                    true),
            new DeliveryPoint(
                    "DORM_TEST_A",
                    "宿舍区测试点A",
                    "宿舍区附近候选配送点，待无人机组复核",
                    new BigDecimal("31.88695000"),
                    new BigDecimal("118.81452000"),
                    new BigDecimal("12.0"),
                    true,
                    false),
            new DeliveryPoint(
                    "TEACHING_TEST_B",
                    "教学区测试点B",
                    "教学区附近候选配送点，待无人机组复核",
                    new BigDecimal("31.88905000"),
                    new BigDecimal("118.81270000"),
                    new BigDecimal("12.0"),
                    true,
                    false)
    );

    public List<AdminDeliveryPointRespVO> listPoints(BigDecimal userLatitude, BigDecimal userLongitude) {
        Optional<DeliveryPoint> recommended = recommend(userLatitude, userLongitude);
        return mockPoints.stream()
                .map(point -> toResp(point, userLatitude, userLongitude, recommended))
                .sorted(pointComparator(userLatitude, userLongitude))
                .toList();
    }

    public AdminDeliveryPointRespVO recommendPoint(BigDecimal userLatitude, BigDecimal userLongitude) {
        validateLatitude("userLatitude", userLatitude);
        validateLongitude("userLongitude", userLongitude);
        DeliveryPoint recommended = recommend(userLatitude, userLongitude)
                .orElseThrow(() -> new IllegalStateException("No enabled delivery point available"));
        return toResp(recommended, userLatitude, userLongitude, Optional.of(recommended));
    }

    private Optional<DeliveryPoint> recommend(BigDecimal userLatitude, BigDecimal userLongitude) {
        if (userLatitude == null || userLongitude == null) {
            return Optional.empty();
        }
        validateLatitude("userLatitude", userLatitude);
        validateLongitude("userLongitude", userLongitude);
        return mockPoints.stream()
                .filter(DeliveryPoint::enabled)
                .min(Comparator.comparingDouble(point -> distanceMeters(userLatitude, userLongitude, point.latitude(), point.longitude())));
    }

    private AdminDeliveryPointRespVO toResp(
            DeliveryPoint point,
            BigDecimal userLatitude,
            BigDecimal userLongitude,
            Optional<DeliveryPoint> recommended) {
        Double distance = userLatitude == null || userLongitude == null
                ? null
                : round(distanceMeters(userLatitude, userLongitude, point.latitude(), point.longitude()), 1);
        boolean isRecommended = recommended.map(value -> value.code().equals(point.code())).orElse(false);
        return new AdminDeliveryPointRespVO(
                point.code(),
                point.name(),
                point.address(),
                point.latitude(),
                point.longitude(),
                point.flightAltitude(),
                point.enabled(),
                point.verified(),
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

    private record DeliveryPoint(
            String code,
            String name,
            String address,
            BigDecimal latitude,
            BigDecimal longitude,
            BigDecimal flightAltitude,
            boolean enabled,
            boolean verified
    ) {
    }
}
