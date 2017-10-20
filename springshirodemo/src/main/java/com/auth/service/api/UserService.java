package com.auth.service.api;

import com.auth.entity.Role;
import com.auth.entity.User;

import java.util.List;

/**
 * @author zhuolin
 * @version 创建时间：2017/10/18 15:42
 * classType:
 */
public interface UserService {

    List<User> queryAllUserInfo();

    User queryUserByUserName(String username);

    Role queryUserRoleByUsername(String username);
}
