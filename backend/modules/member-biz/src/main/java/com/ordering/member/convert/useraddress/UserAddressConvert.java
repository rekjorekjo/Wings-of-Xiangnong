package com.ordering.member.convert.useraddress;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;

import com.ordering.member.controller.app.address.vo.AppUserAddressQueryVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ordering.member.controller.admin.useraddress.vo.*;
import com.ordering.member.dal.dataobject.useraddress.UserAddressDO;

/**
 * 用户地址 Convert
 *
 * @author project team
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
