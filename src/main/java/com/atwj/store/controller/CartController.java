package com.atwj.store.controller;

import com.atwj.store.Vo.CartVo;
import com.atwj.store.service.CartService;
import com.atwj.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-23 22:27
 */
@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    private CartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> addToCart(Integer pid, Integer amount, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行添加到购物车
        cartService.addToCart(uid, pid, amount, username);
        // 返回成功
        return new JsonResult<Void>(OK);
    }

    @RequestMapping({"", "/"})
    public JsonResult<List<CartVo>> findVoById(HttpSession session) {
        // 从Session中获取uid
        Integer uid = getUidFromSession(session);
        List<CartVo> resultVo = cartService.findCartVoById(uid);
        return new JsonResult<>(OK, resultVo);
    }

    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addCartNum(@PathVariable("cid") Integer cid, HttpSession session) {
        // 从Session中获取uid
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        Integer newCartNum = cartService.addCartNum(cid, uid, username);
        return new JsonResult<>(OK, newCartNum);
    }

    @RequestMapping("{cid}/num/delete")
    public JsonResult<Integer> delCartNum(@PathVariable("cid") Integer cid, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        Integer newCartNum = cartService.delCartNum(cid, uid, username);
        return new JsonResult<>(OK, newCartNum);
    }

    @RequestMapping("{cid}/num/deleteCart")
    public JsonResult<Void> deleteCart(@PathVariable("cid") Integer cid, HttpSession session){
        Integer uid = getUidFromSession(session);
        cartService.delCart(cid,uid);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/order_list")
    public JsonResult<List<CartVo>> findVoByCid(Integer[] cids,HttpSession session) {
        // 从Session中获取uid
        Integer uid = getUidFromSession(session);
        List<CartVo> orderCartVo = cartService.findCartVoByCid(cids, uid);
        return new JsonResult<>(OK, orderCartVo);
    }
}
