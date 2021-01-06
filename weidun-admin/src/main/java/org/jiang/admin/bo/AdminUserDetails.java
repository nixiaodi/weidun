package org.jiang.admin.bo;

import org.jiang.model.UwdResource;
import org.jiang.model.UwdUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description SpringSecurity需要的用户详情
 * @Author jiang
 * @Create 2021/1/6
 * @Version 1.0
 */
public class AdminUserDetails implements UserDetails {
    private UwdUser UwdUser;
    private List<UwdResource> resourceList;

    public AdminUserDetails(UwdUser UwdUser, List<UwdResource> resourceList) {
        this.UwdUser = UwdUser;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 返回当前用户的角色
        return resourceList.stream()
                .map(role -> new SimpleGrantedAuthority(role.getId() + ":" + role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return UwdUser.getPassword();
    }

    @Override
    public String getUsername() {
        return UwdUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return UwdUser.getStatus().equals(1);
    }
}
