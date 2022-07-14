package com.atwj.store.mapper;

import com.atwj.store.Vo.CartVo;
import com.atwj.store.entity.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-23 20:53
 */
public interface CartMapper {

    /**
     * 插入购物车数据
     * @param cart 添加的购物车对象
     * @return java.lang.Integer
     */
    Integer insert(Cart cart);

    /**
     * 修改购物车数据中商品的数量
     * @param cid 购物车数据的id
     * @param num 新的数量
     * @param modifiedUser 修改执行人
     * @param date 修改时间
     * @return java.lang.Integer
     */
    Integer updateCartNum(@Param("cid") Integer cid,
                          @Param("num") Integer num,
                          @Param("modifiedUser") String modifiedUser,
                          @Param("modifiedTime") Date date);

    /**
     * 根据用户id和商品id查询购物车中的数据
     * @param uid 用户id
     * @param pid 商品id
     * @return 匹配的购物车数据，如果该用户的购物车中并没有该商品，则返回null
     */
    Cart findByUidAndPid(
            @Param("uid") Integer uid,
            @Param("pid") Integer pid);

    /**
     * 查询用户购物车商品信息
     * @param uid 用户id
     * @return java.util.List<com.atwj.store.Vo.CartVo>
     */
    List<CartVo> findVoById(Integer uid);

    /**
     * 根据购物车id查找购物车信息
     * @param cid
     * @return com.atwj.store.entity.Cart
     */
    Cart findCartById(Integer cid);

    /**
     * 删除购物车中某条商品数据
     * @param cid
     * @return java.lang.Integer
     */
    Integer delCart(Integer cid);

    /**
     * 根据cid数组查询到商品
     * @param cids
     * @return java.util.List<com.atwj.store.Vo.CartVo>
     */
    List<CartVo> findVoByCids(Integer[] cids);

}
