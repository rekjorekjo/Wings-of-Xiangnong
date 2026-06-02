package com.ordering.shop.service.shopservice;

import com.ordering.shop.dal.dataobject.shopservice.ShopServiceDO;
import com.ordering.shop.dal.mysql.shopservice.ShopServiceMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;

/**
 * 我的服务 Service 实现类
 *
 * @author project team
 */
@Service
@Validated
public class AppShopServiceImpl extends ServiceImpl<ShopServiceMapper, ShopServiceDO> implements AppShopService {

    @Resource
    private ShopServiceMapper shopServiceMapper;



}
