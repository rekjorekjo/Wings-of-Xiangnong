package com.wings.module.product.service.storeproductattr;

import com.wings.module.product.controller.app.product.vo.AppStoreProductAttrQueryVo;
import com.wings.module.product.convert.storeproductattr.StoreProductAttrConvert;
import com.wings.module.product.dal.dataobject.storeproductattr.StoreProductAttrDO;
import com.wings.module.product.dal.dataobject.storeproductattrvalue.StoreProductAttrValueDO;
import com.wings.module.product.dal.mysql.storeproductattr.StoreProductAttrMapper;
import com.wings.module.product.service.storeproduct.dto.AttrValueDto;
import com.wings.module.product.service.storeproductattrvalue.StoreProductAttrValueService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import jakarta.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static com.wings.framework.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 商品属性 Service 实现类
 *
 * @author wings
 */
@Service
@Validated
public class AppStoreProductAttrServiceImpl extends ServiceImpl<StoreProductAttrMapper,StoreProductAttrDO> implements AppStoreProductAttrService {

    @Resource
    private StoreProductAttrValueService storeProductAttrValueService;

    /**
     * 获取商品sku属性
     * @param productId 商品id
     * @return map
     */
    @Override
    public Map<String, Object> getProductAttrDetail(long productId) {
        List<StoreProductAttrDO>  storeProductAttrs = this.baseMapper
                .selectList(Wrappers.<StoreProductAttrDO>lambdaQuery()
                        .eq(StoreProductAttrDO::getProductId,productId)
                        .orderByAsc(StoreProductAttrDO::getAttrValues));

        List<StoreProductAttrValueDO>  productAttrValues = storeProductAttrValueService
                .list(Wrappers.<StoreProductAttrValueDO>lambdaQuery()
                        .eq(StoreProductAttrValueDO::getProductId,productId));


        Map<String, StoreProductAttrValueDO> map = productAttrValues.stream()
                .collect(Collectors.toMap(StoreProductAttrValueDO::getSku, p -> p));

        List<AppStoreProductAttrQueryVo> yxStoreProductAttrQueryVoList = new ArrayList<>();

        for (StoreProductAttrDO attr : storeProductAttrs) {
            List<String> stringList = Arrays.asList(attr.getAttrValues().split(","));
            List<AttrValueDto> attrValueDTOS = new ArrayList<>();
            for (String str : stringList) {
                AttrValueDto attrValueDTO = new AttrValueDto();
                attrValueDTO.setAttr(str);
                attrValueDTOS.add(attrValueDTO);
            }

            AppStoreProductAttrQueryVo attrQueryVo = StoreProductAttrConvert.INSTANCE.convert(attr);
            attrQueryVo.setAttrValue(attrValueDTOS);
            attrQueryVo.setAttrValueArr(stringList);

            yxStoreProductAttrQueryVoList.add(attrQueryVo);
        }

        Map<String, Object> returnMap = new LinkedHashMap<>(2);
        returnMap.put("productAttr",yxStoreProductAttrQueryVoList);
        returnMap.put("productValue",map);

        return returnMap;
    }

}
