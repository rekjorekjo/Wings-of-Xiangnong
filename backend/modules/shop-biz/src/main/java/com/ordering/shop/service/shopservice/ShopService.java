package com.ordering.shop.service.shopservice;

import java.util.*;
import jakarta.validation.*;
import com.ordering.shop.controller.admin.shopservice.vo.*;
import com.ordering.shop.dal.dataobject.shopservice.ShopServiceDO;
import com.ordering.framework.common.pojo.PageResult;

/**
 * 我的服务 Service 接口
 *
 * @author project team
 */
public interface ShopService {

    /**
     * 创建我的服务
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createService(@Valid ShopServiceCreateReqVO createReqVO);

    /**
     * 更新我的服务
     *
     * @param updateReqVO 更新信息
     */
    void updateService(@Valid ShopServiceUpdateReqVO updateReqVO);

    /**
     * 删除我的服务
     *
     * @param id 编号
     */
    void deleteService(Long id);

    /**
     * 获得我的服务
     *
     * @param id 编号
     * @return 我的服务
     */
    ShopServiceDO getService(Long id);

    /**
     * 获得我的服务列表
     *
     * @param ids 编号
     * @return 我的服务列表
     */
    List<ShopServiceDO> getServiceList(Collection<Long> ids);

    /**
     * 获得我的服务分页
     *
     * @param pageReqVO 分页查询
     * @return 我的服务分页
     */
    PageResult<ShopServiceDO> getServicePage(ShopServicePageReqVO pageReqVO);

    /**
     * 获得我的服务列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 我的服务列表
     */
    List<ShopServiceDO> getServiceList(ShopServiceExportReqVO exportReqVO);

}
