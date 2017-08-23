package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.bean.IdleBean;
import com.yiwu.changething.sec1.bean.ShareBean;
import com.yiwu.changething.sec1.mapper.ShareMapper;
import com.yiwu.changething.sec1.model.OrderModel;
import com.yiwu.changething.sec1.bean.UserBean;
import com.yiwu.changething.sec1.enums.OrderStatusType;
import com.yiwu.changething.sec1.exception.ErrorBuilder;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.mapper.IdleMapper;
import com.yiwu.changething.sec1.mapper.OrderMapper;
import com.yiwu.changething.sec1.mapper.UserMapper;
import com.yiwu.changething.sec1.bean.OrderBean;
import com.yiwu.changething.sec1.model.ShareModel;
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

    @Autowired
    private ShareMapper shareMapper;

    /**
     * 新增订单信息
     *
     * @param orderBean
     */
    public void insert(OrderModel orderBean) {
        orderBean.setId(UUID.randomUUID().toString());
        orderBean.setStatus(OrderStatusType.NOTPAY);
        orderBean.setDuration(orderBean.getCycleNum() * orderBean.getShareCycle());
        orderMapper.insert(orderBean);
    }

    /**
     * 修改订单信息
     *
     * @param orderBean
     */
    public void update(OrderModel orderBean) {
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
    public OrderBean getOrderById(String orderId) {
        return checkOrderExist(orderId);
    }

    /**
     * 验证订单是否存在
     *
     * @param orderId
     */
    private OrderBean checkOrderExist(String orderId) {
        OrderBean orderModel = orderMapper.getOrderById(orderId);
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
        OrderBean orderModel = checkOrderExist(orderId);
        //卖家用户扣值
        deduct(orderModel.getShareValue(), request);
        //更新订单状态
        orderMapper.updateStatus(orderId, status);
        //卖家用户充值
        recharge(orderModel.getIdleId(), orderModel.getShareValue());
        //更新商品共享次数
        updateShareNum(orderModel.getIdleId());
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
        if (idleBean == null) {
            throw new YwException(ErrorBuilder.E101007);
        }
        UserBean seller = userMapper.getById(idleBean.getCreateBy());
        if (seller == null) {
            throw new YwException(ErrorBuilder.E101012);
        }
        Integer sellerValue = userMapper.getShareValue(seller.getId());
        userMapper.updateShareValue(sellerValue + shareValue, seller.getId());
    }

    /**
     * 更新商品共享次数
     *
     * @param idleId
     */
    public void updateShareNum(String idleId) {
        //当商品已完成订单时，更新商品共享次数
        Integer idleCount = orderMapper.getOrderCountByIdleId(idleId);
        if (idleCount > 0) {
            ShareBean shareBean = shareMapper.getShareByIdleId(idleId);
            ShareModel updateShare = new ShareModel();
            updateShare.setNum(shareBean.getNum() + 1);//商品共享次数加一
            updateShare.setId(shareBean.getId());
            shareMapper.update(updateShare);
        }
    }

    /**
     * 共享订单续费
     *
     * @param orderId
     */
    public void renew(String orderId, Integer cycleNum, HttpServletRequest request) {
        OrderBean orderModel = checkOrderExist(orderId);
        Integer cost = cycleNum * orderModel.getShareCycle() * orderModel.getShareValue();
        //买家扣值
        deduct(cost, request);
        //卖家充值
        recharge(orderModel.getIdleId(), cost);
        //更新订单状态、修改订单周期次数
        orderMapper.renewOrder(orderId, cycleNum + orderModel.getCycleNum());
        //更新订单剩余时长
        orderMapper.updateDuration(orderId, orderModel.getDuration() + cycleNum * orderModel.getShareCycle());
    }
}
