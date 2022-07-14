package com.atwj.store.mapper;

import com.atwj.store.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-15 14:45
 */
public interface UserMapper {
    Integer insert(User user);
    User findByUserName(String username);

    /**
     * 根据uid更新用户的密码
     * @param uid 用户的id
     * @param password 新密码
     * @param modifiedUser 最后修改执行人
     * @param modifiedTime 最后修改时间
     * @return 受影响的行数
     */
    Integer updatePasswordByUid(@Param("uid") Integer uid,
                                @Param("password") String password,
                                @Param("modifiedUser") String modifiedUser,
                                @Param("modifiedTime") Date modifiedTime);

    /**
     * 根据用户id查询用户数据
     * @param uid 用户id
     * @return 匹配的用户数据，如果没有匹配的用户数据，则返回null
     */
    User findByUid(Integer uid);

    /**
     * 修改用户基础信息
     * @author 吴先森
     * @date 2022/5/18 23:01
     * @param user
     * @return java.lang.Integer
     */
    Integer updateInfoByUid(User user);

    /**
     * 上传头像
     * @author 吴先森
     * @date 2022/5/21 14:29
     * @param uid
     * @param avatar
     * @param modifiedUser
     * @param modifiedTime
     * @return java.lang.Integer
     */
    Integer updateAvatarByUid(@Param("uid") Integer uid,
                              @Param("avatar") String avatar,
                              @Param("modifiedUser") String modifiedUser,
                              @Param("modifiedTime") Date modifiedTime);
}
