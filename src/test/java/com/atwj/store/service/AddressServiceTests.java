package com.atwj.store.service;

import com.atwj.store.entity.Address;
import com.atwj.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-21 20:44
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    @Autowired
    private AddressService addressService;

    @Test
    public void insertTest() {
        try {
            Integer uid = 20;
            String username = "管理员";
            Address address = new Address();
            address.setName("张三");
            address.setPhone("17858805555");
            address.setAddress("雁塔区小寨华旗");
            addressService.addNewAddress(uid, username, address);
            System.out.println("OK.");
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void delete(){
        addressService.deleteById(11,6,"wj");
        System.out.println("ok");
    }
}
