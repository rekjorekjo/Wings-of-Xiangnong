package com.wings.module.member.convert.useraddress;

import java.util.*;

import com.wings.framework.common.pojo.PageResult;

import com.wings.module.member.controller.app.address.vo.AppUserAddressQueryVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.wings.module.member.controller.admin.useraddress.vo.*;
import com.wings.module.member.dal.dataobject.useraddress.UserAddressDO;

/**
 * 用户地址 Convert
 *
 * @author wings
 */
@Mapper
public interface UserAddressConvert {

    UserAddressConvert INSTANCE = Mappers.getMapper(UserAddressConvert.class);

    UserAddressDO convert(UserAddressCreateReqVO bean);

    UserAddressDO convert(UserAddressUpdateReqVO bean);

    UserAddressRespVO convert(UserAddressDO bean);

    List<UserAddressRespVO> convertList(List<UserAddressDO> list);

    List<AppUserAddressQueryVo> convertList02(List<UserAddressDO> list);

    PageResult<UserAddressRespVO> convertPage(PageResult<UserAddressDO> page);

}
