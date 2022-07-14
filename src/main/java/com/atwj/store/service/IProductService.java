package com.atwj.store.service;

import com.atwj.store.entity.Product;

import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-23 19:34
 */
public interface IProductService {
    /**
     * 查询热销商品的前四名
     * @return 热销商品前四名的集合
     */
    List<Product> findHotList();

    /**
     * 根据商品id查询商品详情
     * @param id 商品id
     * @return 匹配的商品详情，如果没有匹配的数据则返回null
     */
    Product findById(Integer id);

    /**
     * 查询最新上架商品的前四名
     * @return java.util.List<com.atwj.store.entity.Product>
     */
    List<Product> findNewList();

}
