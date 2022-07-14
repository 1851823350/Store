package com.atwj.store.controller;

import com.atwj.store.Vo.CartVo;
import com.atwj.store.entity.Order;
import com.atwj.store.entity.OrderItem;
import com.atwj.store.service.OrderService;
import com.atwj.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-28 14:59
 */
@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @RequestMapping("/create")
    public JsonResult<Order> createOrder(Integer aid, Integer[] cids, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        Order order = orderService.createOrder(aid, cids, uid, username);
        return new JsonResult<Order>(OK, order);
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<OrderItem>> findItemById(HttpSession session) {
        // 从Session中获取uid
        Integer uid = getUidFromSession(session);
        List<OrderItem> orderList = orderService.findOrderItemsById(uid);
        return new JsonResult<>(OK,orderList);
    }
}
