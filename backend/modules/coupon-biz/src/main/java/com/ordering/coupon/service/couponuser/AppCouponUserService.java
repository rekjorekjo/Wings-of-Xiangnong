package com.ordering.coupon.service.couponuser;

import com.ordering.coupon.controller.app.coupon.vo.AppMyCouponVO;
import com.ordering.coupon.dal.dataobject.couponuser.CouponUserDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户领的优惠券 Service 接口
 *
 * @author project team
 */
public interface AppCouponUserService extends IService<CouponUserDO> {

    /**
     * 获取我的优惠券列表
     * @param shopId 店铺id
     * @param type
     * @param page
     * @param pagesize
     * @return
     */
    List<AppMyCouponVO> getList(Long uid, Long shopId,int type, int page, int pagesize);



}
