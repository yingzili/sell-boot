package com.neuedu.sell.controller;

import com.neuedu.sell.VO.ProductCategoryVO;
import com.neuedu.sell.VO.ProductInfoVO;
import com.neuedu.sell.VO.ResultVO;
import com.neuedu.sell.entity.ProductCategory;
import com.neuedu.sell.entity.ProductInfo;
import com.neuedu.sell.service.ProductCategoryService;
import com.neuedu.sell.service.ProductInfoService;
import com.neuedu.sell.utils.ResultVOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/buyer/product")
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO list() {
        //查询所有上架商品
        List<ProductInfo> productInfoList = productInfoService.findUpAll();

        //2.根据商品列表构建一个商品类别编号的集合
        List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }

        //3.根据商品查询对应的类别
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypeList);

        //4.数据拼装
        //1)将元数据集合转化成VO集合
        List<ProductCategoryVO> productCategoryVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            //构建VO对象
            ProductCategoryVO productCategoryVO = new ProductCategoryVO();
            //将原对象数据赋值到VO对象,Spring提供一个叫做BeanUtils类
            BeanUtils.copyProperties(productCategory, productCategoryVO);

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            //将商品元数据转化成商品VO集合
            for (ProductInfo productInfo : productInfoList) {
                if(productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO=new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    //将商品集合vo设置到类别对象中
                    productInfoVOList.add(productInfoVO);
                }

            }
            //将转化好的vo集合设置到类别中
            productCategoryVO.setProductInfoVOList(productInfoVOList);
            //将VO对象放到集合中
            productCategoryVOList.add(productCategoryVO);
        }
        return ResultVOUtils.success(productCategoryVOList);
    }
}
