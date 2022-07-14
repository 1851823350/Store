package com.atwj.store.mapper;

import com.atwj.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-21 20:13
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressMapperTests {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void insert() {
        Address address = new Address();
        address.setUid(18);
        address.setName("admin");
        address.setPhone("17858802974");
        address.setAddress("雁塔区小寨赛格");
        Integer rows = addressMapper.insert(address);
        System.out.println("rows=" + rows);
    }

    @Test
    public void countByUid() {
        Integer uid = 18;
        Integer count = addressMapper.countByUid(uid);
        System.out.println("count=" + count);
    }

    @Test
    public void findByUid() {
        List<Address> list = addressMapper.findByUid(6);
        System.out.println(list);
    }

    @Test
    public void deleteById(){
        Integer rows = addressMapper.deleteById(1);
        System.out.println(rows);
    }
}
