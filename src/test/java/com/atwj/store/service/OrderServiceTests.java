package com.atwj.store.service;

import com.atwj.store.entity.Address;
import com.atwj.store.entity.OrderItem;
import com.atwj.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-21 20:44
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTests {
    @Autowired
    private OrderService orderService;

    @Test
    public void test(){
        List<OrderItem> orderItemsById = orderService.findOrderItemsById(1);
        for (OrderItem item : orderItemsById) {
            System.out.println(item);
        }
    }


}
