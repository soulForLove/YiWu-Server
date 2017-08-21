package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.bean.IdleBean;
import com.yiwu.changething.sec1.bean.OrderBean;
import com.yiwu.changething.sec1.bean.UserBean;
import com.yiwu.changething.sec1.enums.OrderStatusType;
import com.yiwu.changething.sec1.exception.ErrorBuilder;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.mapper.IdleMapper;
import com.yiwu.changething.sec1.mapper.OrderMapper;
import com.yiwu.changething.sec1.mapper.UserMapper;
import com.yiwu.changething.sec1.model.OrderModel;
import com.yiwu.changething.sec1.utils.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IdleMapper idleMapper;

    /**
     * 新增订单信息
     *
     * @param orderBean
     */
    public void insert(OrderBean orderBean) {
        orderBean.setId(UUID.randomUUID().toString());
        orderBean.setStatus(OrderStatusType.NOTPAY);
        orderMapper.insert(orderBean);
    }

    /**
     * 修改订单信息
     *
     * @param orderBean
     */
    public void update(OrderBean orderBean) {
        checkOrderExist(orderBean.getId());
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
        return checkOrderExist(orderId);
    }

    /**
     * 验证订单是否存在
     *
     * @param orderId
     */
    private OrderModel checkOrderExist(String orderId) {
        OrderModel orderModel = orderMapper.getOrderById(orderId);
        if (orderModel == null) {
            throw new YwException(ErrorBuilder.E101010);
        }
        return orderModel;
    }

    /**
     * 更新订单状态
     *
     * @param orderId
     * @param status
     * @param request
     */
    public void updateOrderStatus(String orderId, OrderStatusType status, HttpServletRequest request) {
        OrderModel orderModel = checkOrderExist(orderId);
        //卖家用户扣值
        deduct(orderModel.getShareValue(), request);
        //更新订单状态
        orderMapper.updateStatus(orderId, status);
        //卖家用户充值
        recharge(orderModel.getIdleId(), orderModel.getShareValue());
    }

    /**
     * 买家扣值
     *
     * @param shareValue
     * @param request
     */
    public void deduct(Integer shareValue, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Principal buyer = (Principal) session.getAttribute(SystemVariableService.USER_INFO);
        if (buyer == null) {
            throw new YwException(ErrorBuilder.E101002);
        }
        Integer buyerValue = userMapper.getShareValue(buyer.getId());
        //共享值不足
        if (buyerValue < shareValue) {
            throw new YwException(ErrorBuilder.E101011);
        }
        //买家用户扣值
        userMapper.updateShareValue(buyerValue - shareValue, buyer.getId());
    }

    /**
     * 卖家充值
     *
     * @param idleId
     * @param shareValue
     */
    public void recharge(String idleId, Integer shareValue) {
        IdleBean idleBean = idleMapper.getIdleById(idleId);
        UserBean seller = userMapper.getById(idleBean.getCreateBy());
        Integer sellerValue = userMapper.getShareValue(seller.getId());
        userMapper.updateShareValue(sellerValue + shareValue, seller.getId());
    }
}
