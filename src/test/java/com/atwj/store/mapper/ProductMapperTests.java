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
public class ProductMapperTests {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void findHotList() {
        List<Product> list = productMapper.findHotList();
        System.out.println("count=" + list.size());
        for (Product item : list) {
            System.out.println(item);
        }
    }

    @Test
    public void findNewList(){
        List<Product> newList = productMapper.findNewList();
        System.out.println("count=" + newList.size());
        for (Product item : newList) {
            System.out.println(item);
        }
    }
}