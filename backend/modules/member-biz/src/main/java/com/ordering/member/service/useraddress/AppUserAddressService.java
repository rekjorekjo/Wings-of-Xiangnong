package com.ordering.member.service.useraddress;

import com.ordering.member.controller.app.address.param.AppAddressParam;
import com.ordering.member.controller.app.address.vo.AppUserAddressQueryVo;
import com.ordering.member.dal.dataobject.useraddress.UserAddressDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户地址 Service 接口
 *
 * @author project team
 */
public interface AppUserAddressService extends IService<UserAddressDO> {

    /**
     * 添加或者修改地址
     * @param uid uid
     * @param param AddressParam
     */
    Long addAndEdit(Long uid, AppAddressParam param);


    /**
     * 设置默认地址
     * @param uid uid
     * @param addressId 地址id
     */
    void setDefault(Long uid,String addressId);

    /**
     * 获取用户地址
     * @param uid uid
     * @param page page
     * @param limit limit
     * @return List
     */
    List<AppUserAddressQueryVo> getList(Long uid, int page, int limit);

}
