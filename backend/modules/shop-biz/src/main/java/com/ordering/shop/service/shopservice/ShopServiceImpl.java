package com.ordering.shop.service.shopservice;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ordering.shop.controller.admin.shopservice.vo.*;
import com.ordering.shop.dal.dataobject.shopservice.ShopServiceDO;
import com.ordering.framework.common.pojo.PageResult;

import com.ordering.shop.convert.shopservice.ShopServiceConvert;
import com.ordering.shop.dal.mysql.shopservice.ShopServiceMapper;

import static com.ordering.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ordering.shop.enums.ErrorCodeConstants.*;

/**
 * 我的服务 Service 实现类
 *
 * @author project team
 */
@Service
@Validated
public class ShopServiceImpl implements ShopService {

    @Resource
    private ShopServiceMapper shopServiceMapper;

    @Override
    public Long createService(ShopServiceCreateReqVO createReqVO) {
        // 插入
        ShopServiceDO service = ShopServiceConvert.INSTANCE.convert(createReqVO);
        shopServiceMapper.insert(service);
        // 返回
        return service.getId();
    }

    @Override
    public void updateService(ShopServiceUpdateReqVO updateReqVO) {
        // 校验存在
        validateServiceExists(updateReqVO.getId());
        // 更新
        ShopServiceDO updateObj = ShopServiceConvert.INSTANCE.convert(updateReqVO);
        shopServiceMapper.updateById(updateObj);
    }

    @Override
    public void deleteService(Long id) {
        // 校验存在
        validateServiceExists(id);
        // 删除
        shopServiceMapper.deleteById(id);
    }

    private void validateServiceExists(Long id) {
        if (shopServiceMapper.selectById(id) == null) {
            throw exception(SERVICE_NOT_EXISTS);
        }
    }

    @Override
    public ShopServiceDO getService(Long id) {
        return shopServiceMapper.selectById(id);
    }

    @Override
    public List<ShopServiceDO> getServiceList(Collection<Long> ids) {
        return shopServiceMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ShopServiceDO> getServicePage(ShopServicePageReqVO pageReqVO) {
        return shopServiceMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ShopServiceDO> getServiceList(ShopServiceExportReqVO exportReqVO) {
        return shopServiceMapper.selectList(exportReqVO);
    }

}
