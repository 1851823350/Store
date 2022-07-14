package com.atwj.store.service;

import com.atwj.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-15 16:50
 */
public interface IUserService {
    //注册用户
    void reg(User user);

    //用户登陆
    User login(String username, String password);

    //修改密碼
    void changePassword(Integer uid, String username, String oldPassword, String newPassword);

    //根据用户id查找用户
    User getUserById(Integer uid);

    //修改用户信息
    void changeInfo(Integer id,String username, User user);

    /**
     * 修改用户头像
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @param avatar 用户的新头像的路径
     */
    void changeAvatar(Integer uid, String username, String avatar);
}
