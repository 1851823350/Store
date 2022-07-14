package com.atwj.store.service.impl;

import com.atwj.store.entity.Address;
import com.atwj.store.mapper.AddressMapper;
import com.atwj.store.service.AddressService;
import com.atwj.store.service.DistrictService;
import com.atwj.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-21 20:36
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private DistrictService districtService;   //辅助增添地址功能模块

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        Integer count = addressMapper.countByUid(uid);
        if(count > 20){
            throw new AddressCountLimitException("你丫的房子这么多？20个地址都不够？你真是够够的0_0");
        }

        address.setUid(uid);
        Integer isDefault = count == 0 ? 1 : 0;
        address.setIsDefault(isDefault);

        //补全四个日志
        address.setCreatedUser(username);
        address.setCreatedTime(new Date());
        address.setModifiedTime(new Date());
        address.setModifiedUser(username);

        //补全省市区的名字信息
        String proName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(proName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        Integer rows = addressMapper.insert(address);
        if(rows != 1){
            throw new InsertException("哦豁，插入地址错误");
        }
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> resultList = addressMapper.findByUid(uid);
        for (Address address : resultList) {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return resultList;
    }

    @Transactional
    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        // 根据参数aid，调用addressMapper中的findByAid()查询收货地址数据
        Address result = addressMapper.findByAid(aid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出AddressNotFoundException
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }

        // 判断查询结果中的uid与参数uid是否不一致(使用equals()判断)
        if (!result.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("非法访问的异常");
        }

        // 调用addressMapper的updateNonDefaultByUid()将该用户的所有收货地址全部设置为非默认，并获取返回受影响的行数
        Integer rows = addressMapper.updateNonDefaultByUid(uid);
        // 判断受影响的行数是否小于1(不大于0)
        if (rows < 1) {
            // 是：抛出UpdateException
            throw new UpdateException("设置默认收货地址时出现未知错误[1]");
        }

        // 调用addressMapper的updateDefaultByAid()将指定aid的收货地址设置为默认，并获取返回的受影响的行数
        rows = addressMapper.updateDefaultByAid(aid, username, new Date());
        // 判断受影响的行数是否不为1
        if (rows != 1) {
            // 是：抛出UpdateException
            throw new UpdateException("设置默认收货地址时出现未知错误[2]");
        }
    }

    @Override
    public void deleteById(Integer aid, Integer uid, String username) {
        Address resultAidAddress = addressMapper.findByAid(aid);
        if(resultAidAddress == null){
            throw new AddressNotFoundException("删除的地址信息不存在");
        }

        Integer rows = addressMapper.deleteById(aid);
        if(rows != 1){
            throw new DeleteException("发生删除异常，请联系管理员");
        }

        //如果删除的不是默认地址，结束业务逻辑
        if(resultAidAddress.getIsDefault() == 0){
            return;
        }

        //查询用户是否还存在除了默认地址以外的地址信息
        Integer resultCount = addressMapper.countByUid(uid);
        if(resultCount == 0){
            return;
        }

        Address lastModified = addressMapper.findLastModified(uid);
        if(lastModified == null){
            throw new AddressNotFoundException("未查到最仅更新的地址信息");
        }
        Date date = new Date();
        Integer row = addressMapper.updateDefaultByAid(lastModified.getAid(), username, date);
        if(row != 1){
            throw new UpdateException("设置默认地址时发生异常");
        }
    }

    @Override
    public Address getAddressById(Integer aid, Integer uid) {
        Address resultAddress = addressMapper.findByAid(aid);
        if(resultAddress == null){
            throw new AddressNotFoundException("地址未找到");
        }

        if(!resultAddress.getUid().equals(uid)){
            throw new AccessDeniedException("非法访问");
        }

        //将没用的信息设置为null，提升数据传输性能
        resultAddress.setProvinceCode(null);
        resultAddress.setCityCode(null);
        resultAddress.setAreaCode(null);
        resultAddress.setCreatedUser(null);
        resultAddress.setCreatedTime(null);
        resultAddress.setModifiedUser(null);
        resultAddress.setModifiedTime(null);

        return resultAddress;
    }
}
