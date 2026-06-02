package com.ordering.module.coupon.service.couponuser;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ordering.module.coupon.controller.admin.couponuser.vo.*;
import com.ordering.module.coupon.dal.dataobject.couponuser.CouponUserDO;
import com.ordering.framework.common.pojo.PageResult;

import com.ordering.module.coupon.convert.couponuser.CouponUserConvert;
import com.ordering.module.coupon.dal.mysql.couponuser.CouponUserMapper;

import static com.ordering.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ordering.module.coupon.enums.ErrorCodeConstants.*;

/**
 * 用户领的优惠券 Service 实现类
 *
 * @author project team
 */
@Service
@Validated
public class CouponUserServiceImpl implements CouponUserService {

    @Resource
    private CouponUserMapper userMapper;

    @Override
    public Integer createUser(CouponUserCreateReqVO createReqVO) {
        // 插入
        CouponUserDO user = CouponUserConvert.INSTANCE.convert(createReqVO);
        userMapper.insert(user);
        // 返回
        return user.getId();
    }

    @Override
    public void updateUser(CouponUserUpdateReqVO updateReqVO) {
        // 校验存在
        validateUserExists(updateReqVO.getId());
        // 更新
        CouponUserDO updateObj = CouponUserConvert.INSTANCE.convert(updateReqVO);
        userMapper.updateById(updateObj);
    }

    @Override
    public void deleteUser(Integer id) {
        // 校验存在
        validateUserExists(id);
        // 删除
        userMapper.deleteById(id);
    }

    private void validateUserExists(Integer id) {
        if (userMapper.selectById(id) == null) {
            throw exception(COUPON_USER_NOT_EXISTS);
        }
    }

    @Override
    public CouponUserDO getUser(Integer id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<CouponUserDO> getUserList(Integer id) {
        CouponUserExportReqVO exportReqVO = new CouponUserExportReqVO();
        exportReqVO.setCouponId(id);
        return userMapper.selectList(exportReqVO);
    }

    @Override
    public PageResult<CouponUserDO> getUserPage(CouponUserPageReqVO pageReqVO) {
        return userMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CouponUserDO> getUserList(CouponUserExportReqVO exportReqVO) {
        return userMapper.selectList(exportReqVO);
    }

}
