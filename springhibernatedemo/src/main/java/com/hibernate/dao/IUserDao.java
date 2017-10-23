/**
 *
 */
package com.hibernate.dao;


import com.hibernate.entity.UserEntity;

/**
 * @author phn
 */
public interface IUserDao {
    boolean isExist(UserEntity user);

    UserEntity findOne(Long id);
}
