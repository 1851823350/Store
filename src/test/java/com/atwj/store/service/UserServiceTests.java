package com.atwj.store.service;

import com.atwj.store.entity.User;
import com.atwj.store.service.ex.ServiceException;
import com.atwj.store.service.impl.IUserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-15 17:16
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTests {
    @Autowired
    private IUserServiceImpl userService;

    @Test
    public void logTest(){
        try {
            User user = new User();
            user.setUsername("wj");
            user.setPassword("123456");
            userService.reg(user);
            System.out.println("注册成功");
        } catch (ServiceException e) {
            System.out.println("注册失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login(){
        User user = userService.login("123", "123123");
        System.out.println(user);
    }

    @Test
    public void changePassword() {
        try {
            Integer uid = 6;
            String username = "lower";
            String oldPassword = "888888";
            String newPassword = "123456";
            userService.changePassword(uid, username, oldPassword, newPassword);
            System.out.println("密码修改成功！");
        } catch (ServiceException e) {
            System.out.println("密码修改失败！" + e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void findByUid(){
        try {
            User user = userService.getUserById(7);
            System.out.println(user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void changeInfo(){
        try {
            User user = new User();
            user.setUsername("2345ds");
            user.setPassword("123456");
            user.setUid(3);
            user.setEmail("4646546@");
            user.setPhone("13233456");
            userService.changeInfo(3,"2345ds",user);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }
}
