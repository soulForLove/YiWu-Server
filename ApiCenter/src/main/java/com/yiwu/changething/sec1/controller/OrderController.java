package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.sec1.model.OrderModel;
import com.yiwu.changething.sec1.enums.OrderStatusType;
import com.yiwu.changething.sec1.bean.OrderBean;
import com.yiwu.changething.sec1.model.PageModel;
import com.yiwu.changething.sec1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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
    public void insert(@RequestBody @Valid OrderModel orderBean, HttpServletRequest request) {
        orderService.insert(orderBean, request);
    }

    /**
     * 修改订单信息
     *
     * @param orderBean
     */
    @PutMapping
    public void update(@RequestBody @Valid OrderModel orderBean) {
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
    public OrderBean getIdleById(@PathVariable String orderId) {
        return orderService.getOrderById(orderId);
    }

    /**
     * 更新订单状态
     *
     * @param orderId
     * @param status
     * @param request
     */
    @PutMapping("/status/{orderId}")
    public void updateOrderStatus(@PathVariable String orderId, @RequestParam OrderStatusType status,
                                  HttpServletRequest request) {
        orderService.updateOrderStatus(orderId, status, request);
    }

    /**
     * 共享订单续费
     *
     * @param orderId
     * @param cycleNum
     * @param request
     */
    @PutMapping("/renew/{orderId}")
    public void renewOrder(@PathVariable("orderId") String orderId, @RequestParam Integer cycleNum,
                           HttpServletRequest request) {
        orderService.renew(orderId, cycleNum, request);
    }

    /**
     * 获取订单信息列表
     *
     * @return
     */
    @GetMapping("/list")
    public Map<String, Object> getShareList(PageModel pageModel) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("orderList", orderService.getOrderList(pageModel.getPage(), pageModel.getPageSize()));
        resultMap.put("totalCount", orderService.countOrderList());
        return resultMap;
    }
}
