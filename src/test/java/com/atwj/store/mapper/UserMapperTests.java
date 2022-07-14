package com.atwj.store.mapper;

import com.atwj.store.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-15 15:15
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void insert(){
        User user = new User();
        user.setUsername("123");
        user.setPassword("123123");
        Integer insert = userMapper.insert(user);
        System.out.println(insert);
    }

    @Test
    public void find(){
        User user = userMapper.findByUserName("123");
        System.out.println(user);
    }

    @Test
    public void update(){
        Integer uid = 1;
        String password = "123456";
        String modifiedUser = "超级管理员";
        Date modifiedTime = new Date();
        Integer rows = userMapper.updatePasswordByUid(uid, password, modifiedUser, modifiedTime);
        System.out.println("rows=" + rows);
    }

    @Test
    public void findByUid(){
        User res = userMapper.findByUid(1);
        System.out.println(res);
    }

    @Test
    public void updateInfoByUid() {
        User user = new User();
        user.setUid(6);
        user.setPhone("17858802222");
        user.setEmail("admin@cy.com");
        user.setGender(0);
        user.setModifiedUser("系统管理员");
        user.setModifiedTime(new Date());
        Integer rows = userMapper.updateInfoByUid(user);
        System.out.println("rows=" + rows);
    }

    @Test
    public void test(){
        System.out.println( 1 / 4);
    }
}
