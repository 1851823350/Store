package com.atwj.store.service;

import com.atwj.store.entity.Address;

import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-21 20:21
 */
public interface AddressService {
    /**
     * 创建新的收货地址
     * @param uid 当前登录的用户的id
     * @param username 当前登录的用户名
     * @param address 用户提交的收货地址数据
     */
    void addNewAddress(Integer uid, String username, Address address);

    /**
     * 得到用户所创建的地址信息
     * @author 吴先森
     * @date 2022/5/22 16:18
     * @param uid 
     * @return java.util.List<com.atwj.store.entity.Address>
     */
    List<Address> getByUid(Integer uid);

    /**
     * 设置默认收货地址
     * @param aid 收货地址id
     * @param uid 归属的用户id
     * @param username 当前登录的用户名
     */
    void setDefault(Integer aid, Integer uid, String username);

    /**
     * 根据aid删除地址
     * @param aid
     * @param uid
     * @param username
     */
    void deleteById(Integer aid, Integer uid, String username);

    /**
     * 根据id查找地址
     * @param aid 地址id
     * @param uid 地址所属用户的id
     * @return com.atwj.store.entity.Address
     */
    Address getAddressById(Integer aid, Integer uid);
}
