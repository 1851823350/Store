package com.atwj.store.service;

import com.atwj.store.Vo.CartVo;

import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-23 22:19
 */
public interface CartService {
    /**
     * 将商品添加到购物车
     *
     * @param uid      当前登录用户的id
     * @param pid      商品的id
     * @param amount   增加的数量
     * @param username 当前登录的用户名
     */
    void addToCart(Integer uid, Integer pid, Integer amount, String username);

    /**
     * 查询用户购物车里面商品信息，展示商品信息
     *
     * @param uid
     * @return java.util.List<com.atwj.store.Vo.CartVo>
     */
    List<CartVo> findCartVoById(Integer uid);

    /**
     * 点击增加按钮，增加商品数量
     * @param cid
     * @param uid
     * @param username
     * @return java.lang.Integer
     */
    Integer addCartNum(Integer cid, Integer uid, String username);

    /**
     * 点击减少按钮，减少商品数量
     * @param cid
     * @param uid
     * @param username
     * @return java.lang.Integer
     */
    Integer delCartNum(Integer cid, Integer uid, String username);

    /**
     * 删除商品信息
     * @param cid
     * @param uid
     */
    void delCart(Integer cid, Integer uid);

    /**
     * 通过勾选的cids查询商品
     * @param cids
     * @param uid
     * @return java.util.List<com.atwj.store.Vo.CartVo>
     */
    List<CartVo> findCartVoByCid(Integer[] cids, Integer uid);
}
