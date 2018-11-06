package com.neuedu.sell.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.neuedu.sell.entity.OrderDetail;
import com.neuedu.sell.enums.OrderStatusEnum;
import com.neuedu.sell.enums.PayStatusEnum;
import com.neuedu.sell.utils.Date2LongSerializer;
import com.neuedu.sell.utils.EnumUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

    private  String orderId;

    /* 买家名字 */
    private String buyerName;

    /* 买家电话 */
    private String buyerPhone;

    /* 收货地址 */
    private String buyerAddress;

    /* 买家openid */
    private String buyerOpenid;

    /* 订单总金额 */
    private BigDecimal orderAmount;

    /* 订单状态，0为新下单*/
    private Integer orderStatus =OrderStatusEnum.NEW.getCode();

    /*支付状态，0未支付，1已支付*/
    private Integer payStatus=PayStatusEnum.NOT_PAY.getCode();

    /*创建时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /*修改时间*/
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /*订单详情集合*/
    private List<OrderDetail> orderDetailList;

    public OrderStatusEnum getOrderStatusEnum(){

        return EnumUtil.getEnumByCode(orderStatus,OrderStatusEnum.class);
    }
    public PayStatusEnum getPayStatusEnum(){

        return EnumUtil.getEnumByCode(payStatus,PayStatusEnum.class);
    }
}
