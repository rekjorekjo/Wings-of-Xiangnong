package com.ordering.member.api.user;

import cn.hutool.core.util.IdUtil;
import com.ordering.framework.common.enums.CommonStatusEnum;
import com.ordering.framework.common.enums.ShopCommonEnum;
import com.ordering.member.api.user.dto.MemberUserRespDTO;
import com.ordering.member.api.user.dto.WechatUserDto;
import com.ordering.member.convert.user.UserConvert;
import com.ordering.member.dal.dataobject.user.MemberUserDO;
import com.ordering.member.service.user.MemberUserService;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Collection;
import java.util.List;

/**
 * 会员用户的 API 实现类
 *
 * @author project team
 */
@Service
@Validated
public class MemberUserApiImpl implements MemberUserApi {

    @Resource
    private MemberUserService userService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveWechatMember(WechatUserDto wechatUserDto) {
        MemberUserDO user = new MemberUserDO();
        user.setNickname(wechatUserDto.getNickname());
        user.setAvatar(wechatUserDto.getHeadimgurl());
        // 生成密码
        String password = IdUtil.fastSimpleUUID();
        user.setPassword(encodePassword(password));
        user.setUsername(wechatUserDto.getOpenid());
        user.setLoginType("wechat");
        user.setWxProfile(wechatUserDto);

        userService.save(user);
    }

    @Override
    public MemberUserRespDTO getUser(Long id) {
        MemberUserDO user = userService.getUser(id);
        return UserConvert.INSTANCE.convert2(user);
    }

    @Override
    public List<MemberUserRespDTO> getUsers(Collection<Long> ids) {
        return UserConvert.INSTANCE.convertList2(userService.getUserList(ids));
    }

    @Override
    public List<MemberUserRespDTO> getUserListByNickname(String nickname) {
        return UserConvert.INSTANCE.convertList2(userService.getUserListByNickname(nickname));
    }

    @Override
    public MemberUserRespDTO getUserByMobile(String mobile) {
        return UserConvert.INSTANCE.convert2(userService.getUserByMobile(mobile));
    }

    /**
     * 对密码进行加密
     *
     * @param password 密码
     * @return 加密后的密码
     */
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

}
