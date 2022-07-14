package com.atwj.store.service.impl;

import com.atwj.store.entity.District;
import com.atwj.store.mapper.DistrictMapper;
import com.atwj.store.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-22 11:06
 */
@Service
public class DistrictServiceImpl implements DistrictService {
    @Autowired
    private DistrictMapper districtMapper;
    @Override
    public List<District> getByParent(String parent) {
        List<District> resultList = districtMapper.findByParent(parent);
        //为了避免无效数据的传输影响性能，我们可以把无用的数据设置为null，节省流量
        for (District district : resultList) {
            district.setId(null);
            district.setParent(null);
        }
        return resultList;
    }

    @Override
    public String getNameByCode(String code) {
        String resultCode = districtMapper.findNameByCode(code);
        return resultCode;
    }
}
