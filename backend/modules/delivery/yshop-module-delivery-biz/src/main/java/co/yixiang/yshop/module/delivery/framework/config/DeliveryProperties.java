package co.yixiang.yshop.module.delivery.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@ConfigurationProperties(prefix = "xiangnong.delivery")
public class DeliveryProperties {

    private Boolean mockEnabled = true;
    private Long defaultTenantId = 1L;
    private Long defaultStoreId = 1L;
    private String defaultSiteName = "Xiangnong Station A";
    private BigDecimal defaultLongitude = new BigDecimal("118.817");
    private BigDecimal defaultLatitude = new BigDecimal("31.892");

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
}
