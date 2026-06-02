package com.ordering.framework.apilog.core.service;

import com.ordering.module.infra.api.logger.ApiErrorLogApi;
import com.ordering.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;

/**
 * API 错误日志 Framework Service 实现类
 *
 * 基于 {@link ApiErrorLogApi} 服务，记录错误日志
 *
 * @author project team
 */
@RequiredArgsConstructor
public class ApiErrorLogFrameworkServiceImpl implements ApiErrorLogFrameworkService {

    private final ApiErrorLogApi apiErrorLogApi;

    @Override
    @Async
    public void createApiErrorLog(ApiErrorLogCreateReqDTO reqDTO) {
        apiErrorLogApi.createApiErrorLog(reqDTO);
    }

}
