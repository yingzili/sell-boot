package com.neuedu.sell.service;

import com.neuedu.sell.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {
    /**
     * 查询所有
     * @return
     */
    List<ProductCategory> findAll();

    /**
     * 根据id查询
     * @param categoryId
     * @return
     */
    ProductCategory findOne(Integer categoryId);

    /**
     * 根据类目编号的集合来查询
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    /**
     * 添加/修改
     * @param productCategory
     * @return
     */
    ProductCategory save(ProductCategory productCategory);
}
