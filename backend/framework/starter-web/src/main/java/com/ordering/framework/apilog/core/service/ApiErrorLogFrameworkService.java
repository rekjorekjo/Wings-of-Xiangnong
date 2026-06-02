package com.ordering.framework.apilog.core.service;

import com.ordering.module.infra.api.logger.dto.ApiErrorLogCreateReqDTO;

/**
 * API 错误日志 Framework Service 接口
 *
 * @author project team
 */
public interface ApiErrorLogFrameworkService {

    /**
     * 创建 API 错误日志
     *
     * @param reqDTO API 错误日志
     */
    void createApiErrorLog(ApiErrorLogCreateReqDTO reqDTO);

}
