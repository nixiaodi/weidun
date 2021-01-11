package org.jiang.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jiang.admin.dto.UwdMenuNode;
import org.jiang.admin.service.UwdMenuService;
import org.jiang.common.api.CommonPage;
import org.jiang.common.api.CommonResult;
import org.jiang.model.UwdMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/1/11
 * @Version 1.0
 */
@Api(tags = "UwdMenuController",description = "后台菜单管理")
@RequestMapping("/menu")
@RestController
public class UwdMenuController {
    @Autowired
    private UwdMenuService menuService;

    @ApiOperation("添加后台菜单")
    @PostMapping("/")
    public CommonResult create(@RequestBody UwdMenu menu) {
        int count = menuService.create(menu);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改后台菜单")
    @PutMapping("/{id}")
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UwdMenu menu) {
        int count = menuService.update(id, menu);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("根据ID获取菜单详情")
    @GetMapping("/{id}")
    public CommonResult getItem(@PathVariable Long id) {
        UwdMenu menu = menuService.getItem(id);
        return CommonResult.success(menu);
    }

    @ApiOperation("根据ID删除后台菜单")
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable Long id) {
        int count = menuService.delete(id);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("分页查询后台菜单")
    @GetMapping("/list/{parentId}")
    public CommonResult<CommonPage<UwdMenu>> list(@PathVariable Long parentId,
                                                  @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum) {
        List<UwdMenu> menuList = menuService.list(parentId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(menuList));
    }

    @ApiOperation("树形结构返回所有菜单列表")
    @GetMapping("/treeList")
    public CommonResult<List<UwdMenuNode>> treeList() {
        List<UwdMenuNode> nodeList = menuService.treeList();
        return CommonResult.success(nodeList);
    }

    @ApiOperation("修改菜单显示效果")
    @PostMapping("/updateHidden/{id}")
    public CommonResult updateHidden(@PathVariable Long id,
                                     @RequestParam("hidden") Integer hidden) {
        int count = menuService.updateHidden(id, hidden);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }
}
