package com.atwj.store.mapper;

import com.atwj.store.entity.Order;
import com.atwj.store.entity.OrderItem;

import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-28 13:49
 */
public interface OrderMapper {
    /**
     * 插入订单信息
     * @param order
     * @return java.lang.Integer
     */
    Integer insertOrder(Order order);

    /**
     * 插入订单商品的数据
     * @param orderItem
     * @return java.lang.Integer
     */
    Integer insertOrderItem(OrderItem orderItem);

    /**
     * 查找订单中商品信息
     * @param oid
     * @return java.lang.Integer
     */
    List<OrderItem> findItemById(Integer oid);

    /**
     * 根据用户id查找订单号
     * @param uid
     * @return Integer
     */
    Integer findOrderNum(Integer uid);
}
