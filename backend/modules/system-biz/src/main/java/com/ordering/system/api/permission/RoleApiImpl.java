package com.ordering.system.api.permission;

import com.ordering.system.service.permission.RoleService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Collection;

/**
 * 角色 API 实现类
 *
 * @author project team
 */
@Service
public class RoleApiImpl implements RoleApi {

    @Resource
    private RoleService roleService;

    @Override
    public void validRoleList(Collection<Long> ids) {
        roleService.validateRoleList(ids);
    }
}
