package org.jiang.admin.controller;

import com.sun.scenario.effect.impl.prism.ps.PPSBlend_LIGHTENPeer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jiang.admin.service.UwdResourceService;
import org.jiang.common.api.CommonPage;
import org.jiang.common.api.CommonResult;
import org.jiang.model.UwdResource;
import org.jiang.security.component.DynamicSecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/1/11
 * @Version 1.0
 */
@Api(tags = "UwdResourceController",description = "后台资源管理")
@RestController
@RequestMapping("/resource")
public class UwdResourceController {
    @Autowired
    private UwdResourceService resourceService;
    @Autowired
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @ApiOperation("添加后台资源")
    @PostMapping("/")
    public CommonResult create(@RequestBody UwdResource resource) {
        int count = resourceService.create(resource);
        // 修改资源信息后需要清空资源列表
        dynamicSecurityMetadataSource.clearDataSource();
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改后台资源")
    @PutMapping("/{id}")
    public CommonResult update(@RequestParam Long id,
                               @RequestBody UwdResource resource) {
        int count = resourceService.update(id, resource);
        dynamicSecurityMetadataSource.clearDataSource();
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("根据资源ID获取资源详情")
    @GetMapping("/{id}")
    public CommonResult<UwdResource> getItem(@PathVariable Long id) {
        UwdResource resource = resourceService.getItem(id);
        return CommonResult.success(resource);
    }

    @ApiOperation("根据ID删除后台资源")
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = resourceService.delete(id);
        dynamicSecurityMetadataSource.clearDataSource();
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("分页模糊查询后台资源")
    @GetMapping("/list")
    public CommonResult<CommonPage<UwdResource>> list(@RequestParam(required = false) Long categoryId,
                                                      @RequestParam(required = false) String nameKeyword,
                                                      @RequestParam(required = false) String urlKeyword,
                                                      @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum) {
        List<UwdResource> resourceList = resourceService.list(categoryId, nameKeyword, urlKeyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(resourceList));
    }

    @ApiOperation("查询所有后台资源")
    @GetMapping("/listAll")
    public CommonResult listAll() {
        List<UwdResource> resourceList = resourceService.listAll();
        return CommonResult.success(resourceList);
    }
}
