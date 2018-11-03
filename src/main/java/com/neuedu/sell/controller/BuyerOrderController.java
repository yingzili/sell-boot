package com.neuedu.sell.controller;

import com.neuedu.sell.VO.ResultVO;
import com.neuedu.sell.converter.OrderForm2OrderDTOConverter;
import com.neuedu.sell.dto.OrderDTO;
import com.neuedu.sell.enums.ResultEnum;
import com.neuedu.sell.exception.SellException;
import com.neuedu.sell.form.OrderForm;
import com.neuedu.sell.service.OrderService;
import com.neuedu.sell.utils.ResultVOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
//1.检验参数合法性
        if (bindingResult.hasErrors()) {
            //bindingResult.getFieldError().getDefaultMessage()获取属性异常值
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        //2.转化为orderDTO类型
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        //3.调用业务层去创建订单
        OrderDTO resultDTO = orderService.create(orderDTO);
        //3.5包装结果
        Map<String,String> map=new HashMap<>();
        map.put("orderId",resultDTO.getOrderId());
        //4.返回结果,只返回成功的原因是如果不成功就会抛出异常，不成功的结果在处理异常时返回
        return ResultVOUtils.success(map);
    }

    /*@GetMapping("/list")
    public ResultVO list(@RequestParam("openid")String openid,
                         @RequestParam("page")Integer page,
                         @RequestParam("size")Integer size){
        return null;
    }*/
}
