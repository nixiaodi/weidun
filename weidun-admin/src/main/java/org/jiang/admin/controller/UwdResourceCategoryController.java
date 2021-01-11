package org.jiang.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jiang.admin.service.UwdResourceCategoryService;
import org.jiang.common.api.CommonResult;
import org.jiang.model.UwdResourceCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/1/11
 * @Version 1.0
 */
@Api(tags = "UwdResourceCategoryController",description = "后台资源分类管理")
@RestController
@RequestMapping("/resourceCategory")
public class UwdResourceCategoryController {
    @Autowired
    private UwdResourceCategoryService resourceCategoryService;

    @ApiOperation("查询所有后台资源分类")
    @GetMapping("/listAll")
    public CommonResult<List<UwdResourceCategory>> listAll() {
        List<UwdResourceCategory> resourceCategoryList = resourceCategoryService.listAll();
        return CommonResult.success(resourceCategoryList);
    }

    @ApiOperation("添加后台资源分类")
    @PostMapping("/")
    public CommonResult create(@RequestBody UwdResourceCategory resourceCategory) {
        int count = resourceCategoryService.create(resourceCategory);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改后台资源分类")
    @PutMapping("/{id}")
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UwdResourceCategory resourceCategory) {
        int count = resourceCategoryService.update(id, resourceCategory);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("根据ID删除后台资源")
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = resourceCategoryService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }
}
