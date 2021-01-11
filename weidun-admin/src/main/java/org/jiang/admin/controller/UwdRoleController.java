package org.jiang.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jiang.admin.service.UwdRoleService;
import org.jiang.common.api.CommonPage;
import org.jiang.common.api.CommonResult;
import org.jiang.model.UwdMenu;
import org.jiang.model.UwdResource;
import org.jiang.model.UwdRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.List;

/**
 * @Description TODO
 * @Author jiang
 * @Create 2021/1/11
 * @Version 1.0
 */
@RestController
@Api(tags = "UwdRoleController",description = "后台用户角色管理")
@RequestMapping("/role")
public class UwdRoleController {
    @Autowired
    private UwdRoleService uwdRoleService;

    @ApiOperation("添加角色")
    @PostMapping("/")
    public CommonResult create(@RequestBody UwdRole role) {
        int count = uwdRoleService.create(role);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("修改角色")
    @PutMapping("/{id}")
    public CommonResult update(@PathVariable Long id,@RequestBody UwdRole role) {
        int count = uwdRoleService.update(id, role);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("批量删除角色")
    @DeleteMapping("/")
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        int count = uwdRoleService.delete(ids);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("获取所有角色")
    @GetMapping("/listAll")
    public CommonResult<List<UwdRole>> listAll() {
        List<UwdRole> roleList = uwdRoleService.list();
        return CommonResult.success(roleList);
    }

    @ApiOperation("根据角色名称分页获取角色列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<UwdRole>> list(@RequestParam(value = "keyword",required = false) String keyword,
                                                  @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum) {
        List<UwdRole> roleList = uwdRoleService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(roleList));
    }

    @ApiOperation("修改角色状态")
    @PostMapping("/updateStatus/{id}")
    public CommonResult updateStatus(@PathVariable Long id,
                                     @RequestParam("status") Integer status) {
        UwdRole uwdRole = new UwdRole();
        uwdRole.setStatus(status);
        int count = uwdRoleService.update(id, uwdRole);
        return count > 0 ? CommonResult.success(count) : CommonResult.failed();
    }

    @ApiOperation("获取角色相关菜单")
    @GetMapping("/listMenu/{roleId}")
    public CommonResult listMenu(@PathVariable Long roleId) {
        List<UwdMenu> listMenu = uwdRoleService.listMenu(roleId);
        return CommonResult.success(listMenu);
    }

    @ApiOperation("获取角色相关资源")
    @GetMapping("/listResource/{roleId}")
    public CommonResult listResource(@PathVariable Long roleId) {
        List<UwdResource> listResource = uwdRoleService.listResource(roleId);
        return CommonResult.success(listResource);
    }

    @ApiOperation("给角色分配菜单")
    @PostMapping("/allocMenu")
    public CommonResult allocMenu(@RequestParam Long roleId,
                                  @RequestParam List<Long> menuIds) {
        int count = uwdRoleService.allocMenu(roleId, menuIds);
        return CommonResult.success(count);
    }

    @ApiOperation("给角色分配资源")
    @PostMapping("/allocResource")
    public CommonResult allocResource(@RequestParam Long roleId,
                                  @RequestParam List<Long> resourceIds) {
        int count = uwdRoleService.allocResource(roleId, resourceIds);
        return CommonResult.success(count);
    }
}
