package com.atwj.store.mapper;

import com.atwj.store.entity.Product;

import java.util.List;

/**
 * @author 吴先森
 * @description:
 * @create 2022-05-23 19:29
 */
public interface ProductMapper {
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
     * 找到商品最新的四个商品
     * @return java.util.List<com.atwj.store.entity.Product>
     */
    List<Product> findNewList();
}
