package com.wings.module.coupon.service.coupon;

import com.wings.framework.common.pojo.PageResult;
import com.wings.module.coupon.controller.admin.coupon.vo.CouponCreateReqVO;
import com.wings.module.coupon.controller.admin.coupon.vo.CouponExportReqVO;
import com.wings.module.coupon.controller.admin.coupon.vo.CouponPageReqVO;
import com.wings.module.coupon.controller.admin.coupon.vo.CouponUpdateReqVO;
import com.wings.module.coupon.convert.coupon.CouponConvert;
import com.wings.module.coupon.dal.dataobject.coupon.CouponDO;
import com.wings.module.coupon.dal.mysql.coupon.CouponMapper;
import com.wings.module.store.dal.dataobject.storeshop.StoreShopDO;
import com.wings.module.store.dal.mysql.storeshop.StoreShopMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static com.wings.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.wings.module.coupon.enums.ErrorCodeConstants.COUPON_NOT_EXISTS;

/**
 * 优惠券 Service 实现类
 *
 * @author wings
 */
@Service
@Validated
public class CouponServiceImpl implements CouponService {

    @Resource
    private CouponMapper Mapper;
    @Resource
    private StoreShopMapper storeShopMapper;

    @Override
    public Long create(CouponCreateReqVO createReqVO) {
        // 插入
        CouponDO  couponDO = CouponConvert.INSTANCE.convert(createReqVO);
        StoreShopDO storeShopDO = storeShopMapper.selectById(createReqVO.getShopId());
        couponDO.setShopName(storeShopDO.getName());
        Mapper.insert(couponDO);
        // 返回
        return couponDO.getId();
    }

    @Override
    public void update(CouponUpdateReqVO updateReqVO) {
        // 校验存在
        validateExists(updateReqVO.getId());
        // 更新
        CouponDO updateObj = CouponConvert.INSTANCE.convert(updateReqVO);
        StoreShopDO storeShopDO = storeShopMapper.selectById(updateReqVO.getShopId());
        updateObj.setShopName(storeShopDO.getName());
        Mapper.updateById(updateObj);
    }

    @Override
    public void delete(Long id) {
        // 校验存在
        validateExists(id);
        // 删除
        Mapper.deleteById(id);
    }

    private void validateExists(Long id) {
        if (Mapper.selectById(id) == null) {
            throw exception(COUPON_NOT_EXISTS);
        }
    }

    @Override
    public CouponDO get(Long id) {
        return Mapper.selectById(id);
    }

    @Override
    public List<CouponDO> getList() {
        return Mapper.selectList(new LambdaQueryWrapper<CouponDO>().eq(CouponDO::getShopId,0));
    }

    @Override
    public PageResult<CouponDO> getPage(CouponPageReqVO pageReqVO) {
        return Mapper.selectPage(pageReqVO);
    }

    @Override
    public List<CouponDO> getList(CouponExportReqVO exportReqVO) {
        return Mapper.selectList(exportReqVO);
    }

}
