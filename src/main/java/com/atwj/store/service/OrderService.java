package com.atwj.store.service;

import com.atwj.store.entity.Address;
import com.atwj.store.entity.Order;
import com.atwj.store.entity.OrderItem;

import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-21 20:21
 */
public interface OrderService {
    /**
     * 创建订单
     * @param aid
     * @param cids
     * @param uid
     * @param username
     * @return com.atwj.store.entity.Order
     */
    Order createOrder(Integer aid, Integer[] cids, Integer uid, String username);

    /**
     * 查找订购单中商品信息
     * @param oid
     * @return java.util.List<com.atwj.store.entity.OrderItem>
     */
    List<OrderItem> findOrderItemsById(Integer oid);

    Integer findOrderNum(Integer uid);
}
