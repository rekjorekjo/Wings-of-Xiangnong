package com.ordering.shop.controller.admin.material;

import com.ordering.framework.common.pojo.CommonResult;
import com.ordering.framework.common.pojo.PageResult;
import com.ordering.shop.controller.admin.material.vo.MaterialCreateReqVO;
import com.ordering.shop.controller.admin.material.vo.MaterialPageReqVO;
import com.ordering.shop.controller.admin.material.vo.MaterialRespVO;
import com.ordering.shop.controller.admin.material.vo.MaterialUpdateReqVO;
import com.ordering.shop.convert.material.MaterialConvert;
import com.ordering.shop.dal.dataobject.material.MaterialDO;
import com.ordering.shop.service.material.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

import static com.ordering.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 素材库")
@RestController
@RequestMapping("/shop/material")
@Validated
public class MaterialController {

    @Resource
    private MaterialService materialService;

    @PostMapping("/create")
    @Operation(summary = "创建素材库")
    //@PreAuthorize("@ss.hasPermission('shop:material:create')")
    public CommonResult<Long> createMaterial(@Valid @RequestBody MaterialCreateReqVO createReqVO) {
        return success(materialService.createMaterial(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新素材库")
    //@PreAuthorize("@ss.hasPermission('shop:material:update')")
    public CommonResult<Boolean> updateMaterial(@Valid @RequestBody MaterialUpdateReqVO updateReqVO) {
        materialService.updateMaterial(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除素材库")
    @Parameter(name = "id", description = "编号", required = true)
    //@PreAuthorize("@ss.hasPermission('shop:material:delete')")
    public CommonResult<Boolean> deleteMaterial(@RequestParam("id") Long id) {
        materialService.deleteMaterial(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得素材库")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    //@PreAuthorize("@ss.hasPermission('shop:material:query')")
    public CommonResult<MaterialRespVO> getMaterial(@RequestParam("id") Long id) {
        MaterialDO material = materialService.getMaterial(id);
        return success(MaterialConvert.INSTANCE.convert(material));
    }

    @GetMapping("/list")
    @Operation(summary = "获得素材库列表")
    @Parameter(name = "ids", description = "编号列表", required = true, example = "1024,2048")
    //@PreAuthorize("@ss.hasPermission('shop:material:query')")
    public CommonResult<List<MaterialRespVO>> getMaterialList(@RequestParam("ids") Collection<Long> ids) {
        List<MaterialDO> list = materialService.getMaterialList(ids);
        return success(MaterialConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得素材库分页")
    //@PreAuthorize("@ss.hasPermission('shop:material:query')")
    public CommonResult<PageResult<MaterialRespVO>> getMaterialPage(@Valid MaterialPageReqVO pageVO) {
        PageResult<MaterialDO> pageResult = materialService.getMaterialPage(pageVO);
        return success(MaterialConvert.INSTANCE.convertPage(pageResult));
    }



}
