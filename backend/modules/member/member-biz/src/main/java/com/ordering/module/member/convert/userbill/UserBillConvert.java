package com.ordering.module.member.convert.userbill;

import java.util.*;

import com.ordering.framework.common.pojo.PageResult;

import com.ordering.module.member.controller.app.user.vo.AppUserBillVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.ordering.module.member.controller.admin.userbill.vo.*;
import com.ordering.module.member.dal.dataobject.userbill.UserBillDO;

/**
 * 用户账单 Convert
 *
 * @author project team
 */
@Mapper
public interface UserBillConvert {

    UserBillConvert INSTANCE = Mappers.getMapper(UserBillConvert.class);

    UserBillDO convert(UserBillCreateReqVO bean);

    UserBillDO convert(UserBillUpdateReqVO bean);

    UserBillRespVO convert(UserBillDO bean);

    List<UserBillRespVO> convertList(List<UserBillDO> list);

    List<AppUserBillVO> convertList02(List<UserBillDO> list);

    PageResult<UserBillRespVO> convertPage(PageResult<UserBillDO> page);


}
