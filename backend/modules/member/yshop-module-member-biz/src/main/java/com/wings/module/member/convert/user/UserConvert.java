package com.wings.module.member.convert.user;

import com.wings.framework.common.pojo.PageResult;
import com.wings.module.member.api.user.dto.MemberUserRespDTO;
import com.wings.module.member.controller.admin.user.vo.UserCreateReqVO;
import com.wings.module.member.controller.admin.user.vo.UserRespVO;
import com.wings.module.member.controller.admin.user.vo.UserUpdateReqVO;
import com.wings.module.member.controller.app.user.vo.AppUserInfoRespVO;
import com.wings.module.member.controller.app.user.vo.AppUserQueryVo;
import com.wings.module.member.dal.dataobject.user.MemberUserDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    AppUserInfoRespVO convert(MemberUserDO bean);

    MemberUserRespDTO convert2(MemberUserDO bean);

    AppUserQueryVo convert3(MemberUserDO bean);

    UserRespVO convert4(MemberUserDO bean);

    List<MemberUserRespDTO> convertList2(List<MemberUserDO> list);

    MemberUserDO convert(UserCreateReqVO bean);

    MemberUserDO convert(UserUpdateReqVO bean);

   UserRespVO convert(MemberUserDO bean,Boolean bool);

    List<UserRespVO> convertList(List<MemberUserDO> list);

    PageResult<UserRespVO> convertPage(PageResult<MemberUserDO> page);

}
