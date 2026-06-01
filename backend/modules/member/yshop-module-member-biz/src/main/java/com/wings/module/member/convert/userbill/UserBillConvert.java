package com.wings.module.member.convert.userbill;

import java.util.*;

import com.wings.framework.common.pojo.PageResult;

import com.wings.module.member.controller.app.user.vo.AppUserBillVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import com.wings.module.member.controller.admin.userbill.vo.*;
import com.wings.module.member.dal.dataobject.userbill.UserBillDO;

/**
 * 用户账单 Convert
 *
 * @author wings
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
