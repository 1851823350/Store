package com.atwj.store.service.impl;

import com.atwj.store.Vo.CartVo;
import com.atwj.store.entity.Address;
import com.atwj.store.entity.Order;
import com.atwj.store.entity.OrderItem;
import com.atwj.store.mapper.OrderMapper;
import com.atwj.store.service.AddressService;
import com.atwj.store.service.CartService;
import com.atwj.store.service.OrderService;
import com.atwj.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-28 14:12
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapperr;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(Integer aid, Integer[] cids, Integer uid, String username) {
        // 创建当前时间对象
        Date now = new Date();

        // 根据cids查询所勾选的购物车列表中的数据
        List<CartVo> carts = cartService.findCartVoByCid(cids,uid);

        // 计算这些商品的总价
        long totalPrice = 0;
        for (CartVo cart : carts) {
            totalPrice += cart.getRealPrice() * cart.getNum();
        }

        // 创建订单数据对象
        Order order = new Order();
        // 补全数据：uid
        order.setUid(uid);
        // 查询收货地址数据
        Address address = addressService.getAddressById(aid, uid);
        // 补全数据：收货地址相关的6项
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        // 补全数据：totalPrice
        order.setTotalPrice(totalPrice);
        // 补全数据：status
        order.setStatus(0);
        // 补全数据：下单时间
        order.setOrderTime(now);
        // 补全数据：日志
        order.setCreatedUser(username);
        order.setCreatedTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);
        // 插入订单数据
        Integer rows1 = orderMapperr.insertOrder(order);
        if (rows1 != 1) {
            throw new InsertException("插入订单数据时出现未知错误，请联系系统管理员");
        }

        // 遍历carts，循环插入订单商品数据
        for (CartVo cart : carts) {
            // 创建订单商品数据
            OrderItem item = new OrderItem();
            // 补全数据：setOid(order.getOid())
            item.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            item.setPid(cart.getPid());
            item.setTitle(cart.getTitle());
            item.setImage(cart.getImage());
            item.setPrice(cart.getRealPrice());
            item.setNum(cart.getNum());
            // 补全数据：4项日志
            item.setCreatedUser(username);
            item.setCreatedTime(now);
            item.setModifiedUser(username);
            item.setModifiedTime(now);
            // 插入订单商品数据
            Integer rows2 = orderMapperr.insertOrderItem(item);
            if (rows2 != 1) {
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }
        }
        // 返回
        return order;
    }

    @Override
    public List<OrderItem> findOrderItemsById(Integer uid) {
        Integer oid = orderMapperr.findOrderNum(uid);
        List<OrderItem> itemList = orderMapperr.findItemById(oid);
        return itemList;
    }

    @Override
    public Integer findOrderNum(Integer uid) {
        return orderMapperr.findOrderNum(uid);
    }
}
