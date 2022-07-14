package com.atwj.store.mapper;

import com.atwj.store.entity.Address;
import com.atwj.store.entity.District;
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
public class DistrictMapperTests {
    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void query(){
        List<District> list = districtMapper.findByParent("110100");
        for(District district : list){
            System.out.println(district);
        }
    }


}
