package com.atwj.store.service;

import com.atwj.store.entity.District;

import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-22 11:06
 */
public interface DistrictService {
    /**
     * 获取全国所有省/某省所有市/某市所有区
     * @param parent 父级代号，当获取某市所有区时，使用市的代号；当获取某省所有市时，使用省的代号；当获取全国所有省时，使用"86"作为父级代号
     * @return 全国所有省/某省所有市/某市所有区的列表
     */
    List<District> getByParent(String parent);
    
    /**
     * 根据省/市/区的行政代号获取省/市/区的名称
     * @author 吴先森
     * @date 2022/5/22 15:32
     * @param code 省/市/区的行政代号
     * @return java.lang.String
     */
    String getNameByCode(String code);
}
