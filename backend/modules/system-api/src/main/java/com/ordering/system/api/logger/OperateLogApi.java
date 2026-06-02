package com.ordering.system.api.logger;

import com.ordering.framework.common.pojo.PageResult;
import com.ordering.system.api.logger.dto.OperateLogCreateReqDTO;
import com.ordering.system.api.logger.dto.OperateLogPageReqDTO;
import com.ordering.system.api.logger.dto.OperateLogRespDTO;
import jakarta.validation.Valid;

/**
 * 操作日志 API 接口
 *
 * @author project team
 */
public interface OperateLogApi {

    /**
     * 创建操作日志
     *
     * @param createReqDTO 请求
     */
    void createOperateLog(@Valid OperateLogCreateReqDTO createReqDTO);

    /**
     * 获取指定模块的指定数据的操作日志分页
     *
     * @param pageReqVO 请求
     * @return 操作日志分页
     */
    PageResult<OperateLogRespDTO> getOperateLogPage(OperateLogPageReqDTO pageReqVO);

}
