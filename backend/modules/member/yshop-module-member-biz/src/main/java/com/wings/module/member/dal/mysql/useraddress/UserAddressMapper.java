package com.wings.module.member.dal.mysql.useraddress;

import java.util.*;

import com.wings.framework.common.pojo.PageResult;
import com.wings.framework.mybatis.core.query.LambdaQueryWrapperX;
import com.wings.framework.mybatis.core.mapper.BaseMapperX;
import com.wings.module.member.dal.dataobject.useraddress.UserAddressDO;
import org.apache.ibatis.annotations.Mapper;
import com.wings.module.member.controller.admin.useraddress.vo.*;

/**
 * 用户地址 Mapper
 *
 * @author wings
 */
@Mapper
public interface UserAddressMapper extends BaseMapperX<UserAddressDO> {

    default PageResult<UserAddressDO> selectPage(UserAddressPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserAddressDO>()
                .eqIfPresent(UserAddressDO::getUid, reqVO.getUid())
                .likeIfPresent(UserAddressDO::getRealName, reqVO.getRealName())
                .eqIfPresent(UserAddressDO::getPhone, reqVO.getPhone())
                .eqIfPresent(UserAddressDO::getProvince, reqVO.getProvince())
                .eqIfPresent(UserAddressDO::getCity, reqVO.getCity())
                .eqIfPresent(UserAddressDO::getCityId, reqVO.getCityId())
                .eqIfPresent(UserAddressDO::getDistrict, reqVO.getDistrict())
                .eqIfPresent(UserAddressDO::getDetail, reqVO.getDetail())
                .eqIfPresent(UserAddressDO::getPostCode, reqVO.getPostCode())
                .eqIfPresent(UserAddressDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(UserAddressDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(UserAddressDO::getIsDefault, reqVO.getIsDefault())
                .betweenIfPresent(UserAddressDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserAddressDO::getId));
    }

    default List<UserAddressDO> selectList(UserAddressExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<UserAddressDO>()
                .eqIfPresent(UserAddressDO::getUid, reqVO.getUid())
                .likeIfPresent(UserAddressDO::getRealName, reqVO.getRealName())
                .eqIfPresent(UserAddressDO::getPhone, reqVO.getPhone())
                .eqIfPresent(UserAddressDO::getProvince, reqVO.getProvince())
                .eqIfPresent(UserAddressDO::getCity, reqVO.getCity())
                .eqIfPresent(UserAddressDO::getCityId, reqVO.getCityId())
                .eqIfPresent(UserAddressDO::getDistrict, reqVO.getDistrict())
                .eqIfPresent(UserAddressDO::getDetail, reqVO.getDetail())
                .eqIfPresent(UserAddressDO::getPostCode, reqVO.getPostCode())
                .eqIfPresent(UserAddressDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(UserAddressDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(UserAddressDO::getIsDefault, reqVO.getIsDefault())
                .betweenIfPresent(UserAddressDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserAddressDO::getId));
    }

}
