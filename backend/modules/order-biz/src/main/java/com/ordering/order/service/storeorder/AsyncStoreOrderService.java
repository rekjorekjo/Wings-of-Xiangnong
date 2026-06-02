package com.ordering.order.service.storeorder;

import com.ordering.member.controller.app.user.vo.AppUserOrderCountVo;
import com.ordering.order.controller.admin.storeorder.vo.ShopOrderTimeDataVO;
import com.ordering.order.controller.app.order.vo.AppStoreOrderQueryVo;
import com.ordering.order.dal.dataobject.storeorder.StoreOrderDO;
import com.ordering.order.service.storeorder.dto.OrderTimeDataDto;

/**
 * 异步订单 Service 接口
 *
 * @author project team
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
