package com.auth.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuolin
 * @version 创建时间：2017/10/18 15:39
 * classType:
 */
public class Role {

    private Long id;

    private String roleName;

    private List<Permission> permissionList = new ArrayList<Permission>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
