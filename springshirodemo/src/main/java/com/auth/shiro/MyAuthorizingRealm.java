package com.auth.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zhuolin
 * @version 创建时间：2017/10/18 15:37
 * classType:
 */
public class MyAuthorizingRealm extends AuthorizingRealm {


    // 权限验证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("验证用户");
        String userName = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo simpleAuthenticationInfo = new SimpleAuthorizationInfo();
        if ("admin".equals(userName)) {
            Set<String> roles = new HashSet<String>();
            roles.add("管理员");
            roles.add("普通用户");
            simpleAuthenticationInfo.setRoles(roles);
            Set<String> permissionNames = new HashSet<String>();
            permissionNames.add("管理员权限");
            permissionNames.add("普通用户权限");
            simpleAuthenticationInfo.setStringPermissions(permissionNames);
        } else {
            Set<String> roles = new HashSet<String>();
            roles.add("普通用户");
            simpleAuthenticationInfo.setRoles(roles);
            Set<String> permissionNames = new HashSet<String>();
            permissionNames.add("普通用户权限");
            simpleAuthenticationInfo.setStringPermissions(permissionNames);
        }
        return simpleAuthenticationInfo;

    }

    // 用户验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("验证用户");
        String userName = ((UsernamePasswordToken) authenticationToken).getUsername();
        String password = String.valueOf( ((UsernamePasswordToken) authenticationToken).getPassword());
        if ("admin".equals(userName)) {
            if("123456".equals(password)){
                AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, "123456", "MyAuthorizingRealm");
                return authenticationInfo;
            }else{
                return null;
            }
        } else {
            return null;
        }
    }
}
