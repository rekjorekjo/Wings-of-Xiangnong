package com.ordering.module.coupon.service.coupon;

import com.ordering.framework.common.pojo.PageResult;
import com.ordering.module.coupon.controller.admin.coupon.vo.CouponCreateReqVO;
import com.ordering.module.coupon.controller.admin.coupon.vo.CouponExportReqVO;
import com.ordering.module.coupon.controller.admin.coupon.vo.CouponPageReqVO;
import com.ordering.module.coupon.controller.admin.coupon.vo.CouponUpdateReqVO;
import com.ordering.module.coupon.controller.app.coupon.vo.AppCouponVO;
import com.ordering.module.coupon.controller.app.coupon.vo.AppMyCouponVO;
import com.ordering.module.coupon.dal.dataobject.coupon.CouponDO;
import com.baomidou.mybatisplus.extension.service.IService;

import jakarta.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 优惠券 Service 接口
 *
 * @author project team
 */
public interface AppCouponService extends IService<CouponDO> {

    /**
     * 获取未被领取优惠券
     * @param shopId 店铺id
     * @param page
     * @param pagesize
     * @return
     */
    List<AppCouponVO> getNotList(Long uid, Long shopId, int page, int pagesize);

    /**
     * 领取优惠券
     * @param uid 用户ID
     * @param id  优惠券ID
     * @param code 兑换码
     */
    void receive(Long uid,Long id,String code);

}
