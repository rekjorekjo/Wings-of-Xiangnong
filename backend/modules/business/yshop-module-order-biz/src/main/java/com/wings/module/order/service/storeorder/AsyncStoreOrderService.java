package com.wings.module.order.service.storeorder;

import com.wings.module.member.controller.app.user.vo.AppUserOrderCountVo;
import com.wings.module.order.controller.admin.storeorder.vo.ShoperOrderTimeDataVo;
import com.wings.module.order.controller.app.order.vo.AppStoreOrderQueryVo;
import com.wings.module.order.dal.dataobject.storeorder.StoreOrderDO;
import com.wings.module.order.service.storeorder.dto.OrderTimeDataDto;

/**
 * 异步订单 Service 接口
 *
 * @author wings
 */
public interface AsyncStoreOrderService {

    /**
     * 计算某个用户的订单统计数据
     * @param uid uid>0 取用户 否则取所有
     * @return UserOrderCountVo
     */
    void orderData(Long uid);



    /**
     * 异步后台数据统计
     */
    void getOrderTimeData();



}
