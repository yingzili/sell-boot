package com.neuedu.sell.repository;

import com.neuedu.sell.entity.ProductInfo;
import com.neuedu.sell.enums.ProductStatusEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Test
    public  void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("1234567");
        productInfo.setProductName("冰粥");
        productInfo.setCategoryType(2);
        productInfo.setProductDescription("很好吃");
        productInfo.setProductIcon("http://www.xxxx");
        productInfo.setProductStock(100);
        productInfo.setProductPrice(new BigDecimal(35));
        productInfoRepository.save(productInfo);
    }

    @Test
    public void findAllTest(){
        List<ProductInfo> productInfoList=productInfoRepository.findAll();
        for (ProductInfo productInfo : productInfoList) {
            System.out.println(productInfo);
        }
    }
    @Test
    public void findByProductStatusTest(){
        for (ProductInfo productInfo : productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode())) {
            System.out.println(productInfo);
        }
    }

}