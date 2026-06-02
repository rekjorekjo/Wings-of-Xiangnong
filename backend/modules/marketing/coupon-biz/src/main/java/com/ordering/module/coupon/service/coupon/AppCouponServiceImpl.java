package com.ordering.module.coupon.service.coupon;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ordering.framework.common.enums.ShopCommonEnum;
import com.ordering.framework.common.pojo.PageResult;
import com.ordering.framework.common.util.object.ObjectUtils;
import com.ordering.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.ordering.module.coupon.controller.admin.coupon.vo.CouponCreateReqVO;
import com.ordering.module.coupon.controller.admin.coupon.vo.CouponExportReqVO;
import com.ordering.module.coupon.controller.admin.coupon.vo.CouponPageReqVO;
import com.ordering.module.coupon.controller.admin.coupon.vo.CouponUpdateReqVO;
import com.ordering.module.coupon.controller.app.coupon.vo.AppCouponVO;
import com.ordering.module.coupon.controller.app.coupon.vo.AppMyCouponVO;
import com.ordering.module.coupon.convert.coupon.CouponConvert;
import com.ordering.module.coupon.convert.couponuser.CouponUserConvert;
import com.ordering.module.coupon.dal.dataobject.coupon.CouponDO;
import com.ordering.module.coupon.dal.dataobject.couponuser.CouponUserDO;
import com.ordering.module.coupon.dal.mysql.coupon.CouponMapper;
import com.ordering.module.coupon.dal.mysql.couponuser.CouponUserMapper;
import com.ordering.module.coupon.service.couponuser.AppCouponUserService;
import com.ordering.module.store.dal.dataobject.storeshop.StoreShopDO;
import com.ordering.module.store.dal.mysql.storeshop.StoreShopMapper;
import com.ordering.module.system.api.logger.dto.OperateLogCreateReqDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.ordering.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ordering.module.coupon.enums.ErrorCodeConstants.*;

/**
 * 优惠券 Service 实现类
 *
 * @author project team
 */
@Service
@Validated
public class AppCouponServiceImpl extends ServiceImpl<CouponMapper, CouponDO> implements AppCouponService {

    @Resource
    private CouponMapper couponMapper;
    @Resource
    private AppCouponUserService appCouponUserService;


    /**
     * 获取未被领取优惠券
     * @param shopId 店铺id
     * @param page
     * @param pagesize
     * @return
     */
    @Override
    public List<AppCouponVO> getNotList(Long uid, Long shopId, int page, int pagesize) {
        LocalDateTime nowTime = LocalDateTime.now();
        Page<CouponDO> pageModel = new Page<>(page, pagesize);
        LambdaQueryWrapperX<CouponDO> wrapper = new LambdaQueryWrapperX<>();
        wrapper.eqIfPresent(CouponDO::getShopId, shopId)
                .gt(CouponDO::getEndTime,nowTime)
                .orderByDesc(CouponDO::getWeigh);
        IPage<CouponDO> pageList = this.baseMapper.selectPage(pageModel, wrapper);
        List<AppCouponVO> list = new ArrayList<>();
        for (CouponDO couponDO : pageList.getRecords()) {
            AppCouponVO appCouponVO = CouponConvert.INSTANCE.convert01(couponDO);
            long count = appCouponUserService.count(new LambdaQueryWrapper<CouponUserDO>()
                    .eq(CouponUserDO::getUserId,uid).eq(CouponUserDO::getCouponId,couponDO.getId()));
            if(count > 0){
                appCouponVO.setIsReceive(ShopCommonEnum.DEFAULT_1.getValue());
            }else {
                appCouponVO.setIsReceive(ShopCommonEnum.DEFAULT_0.getValue());
            }
            list.add(appCouponVO);
        }
        return list;
    }

    /**
     * 领取优惠券
     * @param uid 用户ID
     * @param id  优惠券ID
     * @param code 兑换码
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void receive(Long uid, Long id, String code) {
        CouponDO couponDO = null;
        if(id != null){
            couponDO = this.baseMapper.selectById(id);
        }else {
            couponDO = couponMapper.selectOne(CouponDO::getExchangeCode,code);
        }
        if(couponDO == null){
            throw exception(COUPON_NOT_EXISTS);
        }
        if(couponDO.getReceive() >= couponDO.getDistribute()){
            throw exception(COUPON_RECEIVE_ZERO);
        }
        Long couponCount = appCouponUserService.count(new LambdaQueryWrapper<CouponUserDO>()
                .eq(CouponUserDO::getUserId,uid).eq(CouponUserDO::getCouponId,couponDO.getId()));
        if(couponCount > 0){
            throw exception(COUPON_RECEIVED);
        }


        CouponUserDO couponUserDO = BeanUtil.copyProperties(couponDO,CouponUserDO.class,"id");

        couponUserDO.setUserId(uid.intValue());
        couponUserDO.setCouponId(couponDO.getId().intValue());
        couponUserDO.setEndTime(couponDO.getEndTime());
        couponUserDO.setExchangeCode(code);
        appCouponUserService.save(couponUserDO);

        int newRecive = couponDO.getReceive() + 1;
        couponDO.setReceive(newRecive);
        couponMapper.updateById(couponDO);

    }

}
