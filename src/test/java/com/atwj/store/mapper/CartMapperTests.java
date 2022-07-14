package com.atwj.store.mapper;

import com.atwj.store.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-23 19:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartMapperTests {
    @Autowired
    private CartMapper cartMapper;

    @Test
    public void delTest(){
        Integer rows = cartMapper.delCart(3);
    }
}