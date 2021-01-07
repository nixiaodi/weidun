package org.jiang.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.jiang.model.UwdMenu;

import java.util.List;

/**
 * @Description 后台菜单节点封装
 * @Author jiang
 * @Create 2021/1/7
 * @Version 1.0
 */
@Getter
@Setter
public class UwdMenuNode extends UwdMenu {
    @ApiModelProperty(value = "子级菜单")
    private List<UwdMenuNode> children;
}
