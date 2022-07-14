package com.atwj.store.service.impl;

import com.atwj.store.entity.User;
import com.atwj.store.mapper.UserMapper;
import com.atwj.store.service.IUserService;
import com.atwj.store.service.ex.*;
import com.atwj.store.utils.Md5Password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-15 16:51
 */
@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void reg(User user) {
        Md5Password md5PasswordUtil = new Md5Password();
        //通过user参数获得username的值
        String username = user.getUsername();
        //在数据库中查询是否存在username值的用户
        User result = userMapper.findByUserName(username);
        //如果不为空则抛入出异常
        if (result != null) {
            throw new UsernameDuplicateException("用户名已存在");
        }

        //实现用户密码加密
        String salt = UUID.randomUUID().toString().toUpperCase();
        String md5Password = md5PasswordUtil.getMd5Password(user.getPassword(), salt);
        user.setPassword(md5Password);
        // 补全数据：盐值
        user.setSalt(salt);
        //补全数据
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //执行用户注册功能
        Integer insertRows = userMapper.insert(user);
        if (insertRows != 1) {
            throw new InsertException("用户注册中发生未知错误");
        }
    }

    @Override
    public User login(String username, String password) {
        Md5Password md5PasswordUtil = new Md5Password();
        //查找用户
        User result = userMapper.findByUserName(username);
        if (result == null) {
            throw new UserNotFoundException("用户不存在");
        }
        //检测用户密码是否匹配
        String salt = result.getSalt();
        String newMd5Password = md5PasswordUtil.getMd5Password(password, salt);
        String oldMd5Password = result.getPassword();
        if (!newMd5Password.equals(oldMd5Password)) {
            throw new PasswordNotMatchException("用户密码错误");
        }

        //检测用户是否被删除
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户数据不存在");
        }

        // 创建新的User对象,减去大量不需要的数据，只传入重要数据
        // 将查询结果中的uid、username、avatar封装到新的user对象中,这样使得系统性能提升
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(result.getUsername());
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
        Md5Password md5PasswordUtil = new Md5Password();
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        User result = userMapper.findByUid(uid);
        // 检查查询结果是否为null
        // 是：抛出UserNotFoundException异常
        // 检查查询结果中的isDelete是否为1
        // 是：抛出UserNotFoundException异常
        if (result == null || result.getIsDelete() == 1) {
            throw new UserNotFoundException("用户不存在");
        }
        // 从查询结果中取出盐值
        String salt = result.getSalt();
        // 将参数oldPassword结合盐值加密，得到oldMd5Password
        String oldMd5Password = md5PasswordUtil.getMd5Password(oldPassword, salt);
        // 判断查询结果中的password与oldMd5Password是否不一致
        // 是：抛出PasswordNotMatchException异常
        if (!result.getPassword().contentEquals(oldMd5Password)) {
            throw new PasswordNotMatchException("原密码输入错误");
        }
        // 将参数newPassword结合盐值加密，得到newMd5Password
        String newMd5Password = md5PasswordUtil.getMd5Password(newPassword, salt);
        // 判断新密码是否和旧密码相同，相同则报错
        if (newMd5Password.equals(oldMd5Password)) {
            throw new PasswordNotMatchException("新密码和原密码相同");
        }
        // 创建当前时间对象
        Date date = new Date();
        // 调用userMapper的updatePasswordByUid()更新密码，并获取返回值
        Integer rows = userMapper.updatePasswordByUid(uid, newMd5Password, username, date);
        // 判断以上返回的受影响行数是否不为1
        // 是：抛了UpdateException异常
        if (rows != 1) {
            throw new UserNotFoundException("更新密码时发生未知错误");
        }
    }

    @Override
    public User getUserById(Integer uid) {
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        User user = userMapper.findByUid(uid);
        // 判断查询结果是否为null
        // 是：抛出UserNotFoundException异常
        if (user == null) {
            throw new UserNotFoundException("你怎么想的，用户都么的不存在");
        }
        // 判断查询结果中的isDelete是否为1
        // 是：抛出UserNotFoundException异常
        if (user.getIsDelete() == 1) {
            throw new UserNotFoundException("欸欸欸，这人都特么被处理删除了，还想操作？？？");
        }
        // 创建新的User对象
        // 将以上查询结果中的username/phone/email/gender封装到新User对象中
        User result = new User();
        result.setUsername(user.getUsername());
        result.setPhone(user.getPhone());
        result.setEmail(user.getEmail());
        result.setGender(user.getGender());
        // 返回新的User对象
        return result;
    }

    @Override
    public void changeInfo(Integer uid, String username, User user) {
        // 调用userMapper的findByUid()方法，根据参数uid查询用户数据
        User result = userMapper.findByUid(uid);
        // 判断查询结果是否为null
        // 是：抛出UserNotFoundException异常
        if (result == null) {
            throw new UserNotFoundException("你怎么想的，用户都么的不存在，还想改密码？？？");
        }

        // 判断查询结果中的isDelete是否为1
        // 是：抛出UserNotFoundException异常
        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("欸欸欸，这人都特么被处理删除了，还想操作？？？");
        }
        // 向参数user中补全数据：uid
        // 向参数user中补全数据：modifiedUser(username)
        // 向参数user中补全数据：modifiedTime(new Date())
        user.setUid(uid);
        user.setModifiedUser(username);
        Date date = new Date();
        user.setModifiedTime(date);
        // 调用userMapper的updateInfoByUid(User user)方法执行修改，并获取返回值
        Integer rows = userMapper.updateInfoByUid(user);
        // 判断以上返回的受影响行数是否不为1
        // 是：抛出UpdateException异常
        if (rows != 1) {
            throw new UpdateException("wc，发现未知危险入侵，比波比波比波......");
        }
    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        User result = userMapper.findByUid(uid);
        if (result == null) {
            throw new UserNotFoundException("你怎么想的，用户都么的不存在，还想操作它？？？");
        }

        if (result.getIsDelete() == 1) {
            throw new UserNotFoundException("欸欸欸，这人都特么被处理删除了，还想操作？？？");
        }

        Date date = new Date();
        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, date);

        if(rows != 1){
            throw new UpdateException("wc，发现未知危险入侵，比波比波比波......");
        }
    }
}
