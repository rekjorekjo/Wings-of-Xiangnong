package com.ordering.module.shop.service.material;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ordering.module.shop.controller.admin.material.vo.*;
import com.ordering.module.shop.dal.dataobject.material.MaterialDO;
import com.ordering.framework.common.pojo.PageResult;

import com.ordering.module.shop.convert.material.MaterialConvert;
import com.ordering.module.shop.dal.mysql.material.MaterialMapper;

import static com.ordering.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ordering.module.shop.enums.ErrorCodeConstants.*;

/**
 * 素材库 Service 实现类
 *
 * @author project team
 */
@Service
@Validated
public class MaterialServiceImpl implements MaterialService {

    @Resource
    private MaterialMapper materialMapper;

    @Override
    public Long createMaterial(MaterialCreateReqVO createReqVO) {
        // 插入
        MaterialDO material = MaterialConvert.INSTANCE.convert(createReqVO);
        materialMapper.insert(material);
        // 返回
        return material.getId();
    }

    @Override
    public void updateMaterial(MaterialUpdateReqVO updateReqVO) {
        // 校验存在
        validateMaterialExists(updateReqVO.getId());
        // 更新
        MaterialDO updateObj = MaterialConvert.INSTANCE.convert(updateReqVO);
        materialMapper.updateById(updateObj);
    }

    @Override
    public void deleteMaterial(Long id) {
        // 校验存在
        validateMaterialExists(id);
        // 删除
        materialMapper.deleteById(id);
    }

    private void validateMaterialExists(Long id) {
        if (materialMapper.selectById(id) == null) {
            throw exception(MATERIAL_NOT_EXISTS);
        }
    }

    @Override
    public MaterialDO getMaterial(Long id) {
        return materialMapper.selectById(id);
    }

    @Override
    public List<MaterialDO> getMaterialList(Collection<Long> ids) {
        return materialMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MaterialDO> getMaterialPage(MaterialPageReqVO pageReqVO) {
        return materialMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MaterialDO> getMaterialList(MaterialExportReqVO exportReqVO) {
        return materialMapper.selectList(exportReqVO);
    }

}
