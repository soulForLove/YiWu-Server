package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.sec1.bean.OrderBean;
import com.yiwu.changething.sec1.model.OrderModel;
import com.yiwu.changething.sec1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 新增订单信息
     *
     * @param orderBean
     */
    @PostMapping
    public void insert(@RequestBody @Valid OrderBean orderBean) {
        orderService.insert(orderBean);
    }

    /**
     * 修改订单信息
     *
     * @param orderBean
     */
    @PutMapping
    public void update(@RequestBody @Valid OrderBean orderBean) {
        orderService.update(orderBean);
    }

    /**
     * 根據id刪除订单信息
     *
     * @param orderId
     */
    @DeleteMapping("/{orderId}")
    public void deleteById(@PathVariable String orderId) {
        orderService.deleteById(orderId);
    }

    /**
     * 根据id获取订单信息
     *
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public OrderModel getIdleById(@PathVariable String orderId) {
        return orderService.getOrderById(orderId);
    }
}
