package com.neuedu.sell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * 商品类别实体类
 */
@Entity
@Data
public class ProductCategory {

    @Id
    @GeneratedValue
    private Integer categoryId;

    /*类别名*/
    private String categoryName;

    /*类别编号*/
    private Integer categoryType;

}
