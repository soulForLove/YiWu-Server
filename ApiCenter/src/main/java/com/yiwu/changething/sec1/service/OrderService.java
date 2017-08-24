package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.bean.IdleBean;
import com.yiwu.changething.sec1.bean.ShareBean;
import com.yiwu.changething.sec1.enums.ShareStatus;
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
import com.yiwu.changething.sec1.utils.YwSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Autowired
    private YwSecurityUtil ywSecurityUtil;

    @Value("${share.value.rate}")
    private Double shareValueRate;

    /**
     * 新增订单信息
     *
     * @param orderModel
     */
    public void insert(OrderModel orderModel, HttpServletRequest request) {
        Principal principal = ywSecurityUtil.checkUserLogin(request);
        orderModel.setUserId(principal.getId());
        orderModel.setId(UUID.randomUUID().toString());
        orderModel.setStatus(OrderStatusType.NOTPAY);
        orderModel.setDuration(orderModel.getCycleNum() * orderModel.getShareCycle());
        orderMapper.insert(orderModel);
    }

    /**
     * 修改订单信息
     *
     * @param orderModel
     */
    public void update(OrderModel orderModel) {
        checkOrderExist(orderModel.getId());
        orderMapper.update(orderModel);
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
        //买家用户扣值
        deduct(orderModel.getShareValue(), request);
        //更新商品共享次数
        updateShareNum(orderModel.getIdleId());
        //更新订单状态
        orderMapper.updateStatus(orderId, status);
        //卖家用户充值
        recharge(orderModel.getIdleId(), orderModel.getShareValue());
        //更新订单利润
        updateProfit(orderId, orderModel.getProfit() != null ? orderModel.getProfit() : 0.0 +
                orderModel.getShareValue() * shareValueRate);
        //更新商品状态
        idleMapper.updateShareStatus(ShareStatus.LOCK, null, orderModel.getIdleId(), null);

    }

    /**
     * 买家扣值
     *
     * @param shareValue
     * @param request
     */
    public void deduct(Integer shareValue, HttpServletRequest request) {
        Principal currentPrincipal = ywSecurityUtil.checkUserLogin(request);
        Integer buyerValue = userMapper.getShareValue(currentPrincipal.getId());
        //共享值不足
        if (buyerValue < shareValue) {
            throw new YwException(ErrorBuilder.E101011);
        }
        //买家用户扣值
        userMapper.updateShareValue(buyerValue.doubleValue() - shareValue, currentPrincipal.getId());
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
        userMapper.updateShareValue(sellerValue + shareValue * (1 - shareValueRate), seller.getId());
    }

    /**
     * 更新商品共享次数
     *
     * @param idleId
     */
    public void updateShareNum(String idleId) {
        //在商品已完成订单中获取商品共享次数
        Integer idleCount = orderMapper.getOrderCountByIdleId(idleId);
        //更新商品共享次数
        if (idleCount > 0) {
            ShareBean shareBean = shareMapper.getShareByIdleId(idleId);
            if (shareBean == null) {
                //新增共享圈子信息
                insertShare(idleId);
            } else {
                ShareModel updateShare = new ShareModel();
                updateShare.setCycleNum(shareBean.getCycleNum() + 1);//商品共享次数加一
                updateShare.setId(shareBean.getId());
                shareMapper.update(updateShare);
            }
        }
    }

    /**
     * 新增共享圈子信息
     *
     * @param idleId
     */
    public void insertShare(String idleId) {
        IdleBean idle = idleMapper.getIdleById(idleId);
        ShareModel shareModel = new ShareModel();
        shareModel.setCycleNum(1);
        shareModel.setIdleId(idleId);
        shareModel.setUserId(idle.getCreateBy());
        shareModel.setShareCycle(idle.getShareCycle());
        shareModel.setShareValue(idle.getShareValue());
        shareModel.setShareStatus(ShareStatus.NOTLOCK);
        shareMapper.insert(shareModel);
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
        //更新订单利润
        updateProfit(orderId, orderModel.getProfit() != null ? orderModel.getProfit() : 0.0 + cost * shareValueRate);
    }

    /**
     * 定时更新订单剩余时长
     */
    public void reduceDuration() {
        //定时更新订单剩余时长
        orderMapper.reduceDuration();
        List<OrderBean> durationList = orderMapper.getDurationList();
        if (durationList.size() > 0) {
            List<String> idleIds = durationList.stream().map(OrderBean::getIdleId).collect(Collectors.toList());
            List<String> orderIds = durationList.stream().map(OrderBean::getId).collect(Collectors.toList());
            //更新订单状态
            orderMapper.updateBatchStatus(orderIds, OrderStatusType.COMPLETED);
            //更新商品状态
            idleMapper.updateBatchStatus(idleIds);
        }
    }

    /**
     * 更新订单利润
     *
     * @param orderId
     * @param profit
     */
    public void updateProfit(String orderId, Double profit) {
        orderMapper.updateProfit(orderId, profit);
    }
}
