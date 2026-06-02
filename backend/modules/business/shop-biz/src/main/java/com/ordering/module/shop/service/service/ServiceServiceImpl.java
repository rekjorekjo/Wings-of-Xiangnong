package com.ordering.module.shop.service.service;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ordering.module.shop.controller.admin.service.vo.*;
import com.ordering.module.shop.dal.dataobject.service.ServiceDO;
import com.ordering.framework.common.pojo.PageResult;

import com.ordering.module.shop.convert.service.ServiceConvert;
import com.ordering.module.shop.dal.mysql.service.ServiceMapper;

import static com.ordering.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ordering.module.shop.enums.ErrorCodeConstants.*;

/**
 * 我的服务 Service 实现类
 *
 * @author project team
 */
@Service
@Validated
public class ServiceServiceImpl implements ServiceService {

    @Resource
    private ServiceMapper serviceMapper;

    @Override
    public Long createService(ServiceCreateReqVO createReqVO) {
        // 插入
        ServiceDO service = ServiceConvert.INSTANCE.convert(createReqVO);
        serviceMapper.insert(service);
        // 返回
        return service.getId();
    }

    @Override
    public void updateService(ServiceUpdateReqVO updateReqVO) {
        // 校验存在
        validateServiceExists(updateReqVO.getId());
        // 更新
        ServiceDO updateObj = ServiceConvert.INSTANCE.convert(updateReqVO);
        serviceMapper.updateById(updateObj);
    }

    @Override
    public void deleteService(Long id) {
        // 校验存在
        validateServiceExists(id);
        // 删除
        serviceMapper.deleteById(id);
    }

    private void validateServiceExists(Long id) {
        if (serviceMapper.selectById(id) == null) {
            throw exception(SERVICE_NOT_EXISTS);
        }
    }

    @Override
    public ServiceDO getService(Long id) {
        return serviceMapper.selectById(id);
    }

    @Override
    public List<ServiceDO> getServiceList(Collection<Long> ids) {
        return serviceMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ServiceDO> getServicePage(ServicePageReqVO pageReqVO) {
        return serviceMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ServiceDO> getServiceList(ServiceExportReqVO exportReqVO) {
        return serviceMapper.selectList(exportReqVO);
    }

}
