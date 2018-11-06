package com.neuedu.sell.controller;

import com.neuedu.sell.dto.OrderDTO;
import com.neuedu.sell.enums.ResultEnum;
import com.neuedu.sell.exception.SellException;
import com.neuedu.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/seller/order")
public class SellerOrderContraller {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size) {
        //new视图对象
        ModelAndView model = new ModelAndView("order/list");
        //构造分页对象
        PageRequest request = new PageRequest(page - 1, size);
        //通过分页对象查询
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        //将查询结果放入视图对象中
        model.addObject("orderDTOPage", orderDTOPage);
        model.addObject("currentPage", page);
        return model;
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId) {

        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        } catch (SellException e) {
            ModelAndView errorModel=new ModelAndView();
            errorModel.setViewName("/common/error");
            errorModel.addObject("msg",e.getMessage());
            errorModel.addObject("url","/sell/seller/order/list");
            return errorModel;
        }
       ModelAndView model = new ModelAndView("/common/success");
       model.addObject("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMessage());
       model.addObject("url","/sell/seller/order/list");
        return model;
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId){
        OrderDTO orderDTO=new OrderDTO();
        try {
            orderDTO= orderService.findOne(orderId);
        } catch (SellException e) {
            ModelAndView errorModel=new ModelAndView();
            errorModel.setViewName("/common/error");
            errorModel.addObject("msg",e.getMessage());
            errorModel.addObject("url","/sell/seller/order/list");
            return errorModel;
        }
        ModelAndView model = new ModelAndView("/order/detail");
        model.addObject("orderDTO",orderDTO);
        return model;
    }
    @GetMapping("/finish")
    public ModelAndView finish(@RequestParam("orderId") String orderId){
        try {
           OrderDTO orderDTO= orderService.findOne(orderId);
           orderService.finish(orderDTO);
        } catch (SellException e) {
            ModelAndView errorModel=new ModelAndView();
            errorModel.setViewName("/common/error");
            errorModel.addObject("msg",e.getMessage());
            errorModel.addObject("url","/sell/seller/order/list");
            return errorModel;
        }
        ModelAndView model = new ModelAndView("/common/success");
        model.addObject("msg",ResultEnum.ORDER_FINISH_SUCCESS.getMessage());
        model.addObject("url","/sell/seller/order/list");
        return model;
    }
}
