package com.ordering.member.convert.user;

import com.ordering.framework.common.pojo.PageResult;
import com.ordering.member.api.user.dto.MemberUserRespDTO;
import com.ordering.member.controller.admin.user.vo.UserCreateReqVO;
import com.ordering.member.controller.admin.user.vo.UserRespVO;
import com.ordering.member.controller.admin.user.vo.UserUpdateReqVO;
import com.ordering.member.controller.app.user.vo.AppUserInfoRespVO;
import com.ordering.member.controller.app.user.vo.AppUserQueryVo;
import com.ordering.member.dal.dataobject.user.MemberUserDO;
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
