package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.bean.OrderBean;
import com.yiwu.changething.sec1.exception.ErrorBuilder;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.mapper.OrderMapper;
import com.yiwu.changething.sec1.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class OrderService {
    
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 新增订单信息
     *
     * @param orderBean
     */
    public void insert(OrderBean orderBean) {
        orderBean.setId(UUID.randomUUID().toString());
        orderMapper.insert(orderBean);
    }

    /**
     * 修改订单信息
     *
     * @param orderBean
     */
    public void update(OrderBean orderBean) {
        OrderModel idle = orderMapper.getOrderById(orderBean.getId());
        if (idle == null) {
            throw new YwException(ErrorBuilder.E101010);
        }
        orderMapper.update(orderBean);
    }

    /**
     * 根据id删除订单信息
     *
     * @param orderId
     */
    public void deleteById(String orderId) {
        orderMapper.deleteById(orderId);
    }

    /**
     * 根据id获取訂單信息
     *
     * @param orderId
     * @return
     */
    public OrderModel getOrderById(String orderId) {
        OrderModel orderModel = orderMapper.getOrderById(orderId);
        if (orderModel == null) {
            throw new YwException(ErrorBuilder.E101010);
        }
        return orderModel;
    }
}
