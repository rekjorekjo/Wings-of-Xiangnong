package com.ordering.delivery.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "app.delivery")
public class DeliveryProperties {

    private Boolean mockEnabled = true;
    private Long defaultTenantId = 1L;
    private Long defaultStoreId = 1L;
    private String defaultSiteName = "App Station A";
    private BigDecimal defaultLongitude = new BigDecimal("118.8151082");
    private BigDecimal defaultLatitude = new BigDecimal("31.8880093");
    private BigDecimal defaultHomeAltitude = new BigDecimal("20.0");
    private BigDecimal defaultFlightAltitude = new BigDecimal("10.0");

    public Boolean getMockEnabled() {
        return mockEnabled;
    }

    public void setMockEnabled(Boolean mockEnabled) {
        this.mockEnabled = mockEnabled;
    }

    public Long getDefaultTenantId() {
        return defaultTenantId;
    }

    public void setDefaultTenantId(Long defaultTenantId) {
        this.defaultTenantId = defaultTenantId;
    }

    public Long getDefaultStoreId() {
        return defaultStoreId;
    }

    public void setDefaultStoreId(Long defaultStoreId) {
        this.defaultStoreId = defaultStoreId;
    }

    public String getDefaultSiteName() {
        return defaultSiteName;
    }

    public void setDefaultSiteName(String defaultSiteName) {
        this.defaultSiteName = defaultSiteName;
    }

    public BigDecimal getDefaultLongitude() {
        return defaultLongitude;
    }

    public void setDefaultLongitude(BigDecimal defaultLongitude) {
        this.defaultLongitude = defaultLongitude;
    }

    public BigDecimal getDefaultLatitude() {
        return defaultLatitude;
    }

    public void setDefaultLatitude(BigDecimal defaultLatitude) {
        this.defaultLatitude = defaultLatitude;
    }

    public BigDecimal getDefaultHomeAltitude() {
        return defaultHomeAltitude;
    }

    public void setDefaultHomeAltitude(BigDecimal defaultHomeAltitude) {
        this.defaultHomeAltitude = defaultHomeAltitude;
    }

    public BigDecimal getDefaultFlightAltitude() {
        return defaultFlightAltitude;
    }

    public void setDefaultFlightAltitude(BigDecimal defaultFlightAltitude) {
        this.defaultFlightAltitude = defaultFlightAltitude;
    }
}
