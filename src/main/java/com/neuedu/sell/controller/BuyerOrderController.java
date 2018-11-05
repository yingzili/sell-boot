package com.neuedu.sell.controller;

import com.neuedu.sell.VO.ResultVO;
import com.neuedu.sell.converter.OrderForm2OrderDTOConverter;
import com.neuedu.sell.dto.OrderDTO;
import com.neuedu.sell.enums.ResultEnum;
import com.neuedu.sell.exception.SellException;
import com.neuedu.sell.form.OrderForm;
import com.neuedu.sell.service.BuyerService;
import com.neuedu.sell.service.OrderService;
import com.neuedu.sell.utils.ResultVOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
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

    @Autowired
    private BuyerService buyerService;
    /**
     * 订单创建
     * @param orderForm
     * @param bindingResult
     * @return
     */
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

    /**
     * 订单查询
     * @param openid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/list")
    public ResultVO list(@RequestParam("openid")String openid,
                         @RequestParam(value = "page",defaultValue = "0")Integer page,
                         @RequestParam(value = "size",defaultValue = "10")Integer size){
        //1.校验参数合法性
        if (StringUtils.isEmpty(openid)) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        //2.调用业务层去处理
        PageRequest request=new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage=orderService.findList(openid,request);
        //3.包装结果集

        return ResultVOUtils.success(orderDTOPage.getContent());
    }

    /**
     * 订单详情
     * @param openid
     * @param orderId
     * @return
     */

    @GetMapping("/detail")
    public ResultVO detail(@RequestParam("openid")String openid,
                           @RequestParam("orderId")String orderId){
        //1.校验参数合法性
        if (StringUtils.isEmpty(openid)|| StringUtils.isEmpty(orderId)) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        //2.查询
        // TODO 不安全，会有横向越权问题
        OrderDTO orderDTO=buyerService.findOrderOne(openid,orderId);
        return ResultVOUtils.success(orderDTO);
    }

    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openid")String openid,
                           @RequestParam("orderId")String orderId){
        //1.校验参数合法性
        if (StringUtils.isEmpty(openid)|| StringUtils.isEmpty(orderId)) {
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        //2.查询
        // TODO 不安全，会有横向越权问题（添加buyerservice解决此问题)
        OrderDTO resultDTO=buyerService.cancelOrder(openid,orderId);
        return ResultVOUtils.success();
    }
}
