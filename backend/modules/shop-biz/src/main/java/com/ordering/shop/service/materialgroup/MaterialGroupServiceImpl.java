package com.ordering.shop.service.materialgroup;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.ordering.shop.controller.admin.materialgroup.vo.*;
import com.ordering.shop.dal.dataobject.materialgroup.MaterialGroupDO;
import com.ordering.framework.common.pojo.PageResult;

import com.ordering.shop.convert.materialgroup.MaterialGroupConvert;
import com.ordering.shop.dal.mysql.materialgroup.MaterialGroupMapper;

import static com.ordering.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.ordering.shop.enums.ErrorCodeConstants.*;

/**
 * 素材分组 Service 实现类
 *
 * @author project team
 */
@Service
@Validated
public class MaterialGroupServiceImpl implements MaterialGroupService {

    @Resource
    private MaterialGroupMapper materialGroupMapper;

    @Override
    public Long createMaterialGroup(MaterialGroupCreateReqVO createReqVO) {
        // 插入
        MaterialGroupDO materialGroup = MaterialGroupConvert.INSTANCE.convert(createReqVO);
        materialGroupMapper.insert(materialGroup);
        // 返回
        return materialGroup.getId();
    }

    @Override
    public void updateMaterialGroup(MaterialGroupUpdateReqVO updateReqVO) {
        // 校验存在
        validateMaterialGroupExists(updateReqVO.getId());
        // 更新
        MaterialGroupDO updateObj = MaterialGroupConvert.INSTANCE.convert(updateReqVO);
        materialGroupMapper.updateById(updateObj);
    }

    @Override
    public void deleteMaterialGroup(Long id) {
        // 校验存在
        validateMaterialGroupExists(id);
        // 删除
        materialGroupMapper.deleteById(id);
    }

    private void validateMaterialGroupExists(Long id) {
        if (materialGroupMapper.selectById(id) == null) {
            throw exception(MATERIAL_GROUP_NOT_EXISTS);
        }
    }

    @Override
    public MaterialGroupDO getMaterialGroup(Long id) {
        return materialGroupMapper.selectById(id);
    }

    @Override
    public List<MaterialGroupDO> getMaterialGroupList(Collection<Long> ids) {
        return materialGroupMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<MaterialGroupDO> getMaterialGroupPage(MaterialGroupPageReqVO pageReqVO) {
        return materialGroupMapper.selectPage(pageReqVO);
    }

    @Override
    public List<MaterialGroupDO> getMaterialGroupList(MaterialGroupExportReqVO exportReqVO) {
        return materialGroupMapper.selectList(exportReqVO);
    }

}
