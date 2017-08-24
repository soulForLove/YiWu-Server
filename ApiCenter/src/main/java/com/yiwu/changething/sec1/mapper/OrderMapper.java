package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.sec1.model.OrderModel;
import com.yiwu.changething.sec1.enums.OrderStatusType;
import com.yiwu.changething.sec1.bean.OrderBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public interface OrderMapper {
    /**
     * 根据id获取订单
     *
     * @param orderId
     * @return
     */
    OrderBean getOrderById(@Param("orderId") String orderId);

    /**
     * 新增訂單
     *
     * @param orderBean
     */
    void insert(OrderModel orderBean);

    /**
     * 更新訂單
     *
     * @param orderBean
     */
    void update(OrderModel orderBean);

    /**
     * 根據id刪除訂單
     *
     * @param orderId
     */
    void deleteById(@Param("orderId") String orderId);

    /**
     * 更新訂單狀態
     *
     * @param orderId
     * @param status
     */
    void updateStatus(@Param("orderId") String orderId, @Param("status") OrderStatusType status);

    /**
     * 批量更新訂單狀態
     *
     * @param orderIds
     * @param status
     */
    void updateBatchStatus(@Param("orderIds") List<String> orderIds, @Param("status") OrderStatusType status);

    /**
     * 付完款的訂單中商品的共享次數
     *
     * @param idleId
     * @return
     */
    Integer getOrderCountByIdleId(@Param("idleId") String idleId);

    /**
     * 续费：重新設置周期的次數
     *
     * @param orderId
     * @param cycleNum
     */
    void renewOrder(@Param("orderId") String orderId, @Param("cycleNum") Integer cycleNum);

    /**
     * 更新订单时长
     *
     * @param orderId
     * @param duration
     */
    void updateDuration(@Param("orderId") String orderId, @Param("duration") Integer duration);

    /**
     * 每天更新減少時長
     */
    void reduceDuration();

    /**
     * 獲取剩餘時長大於0的訂單列表
     *
     * @return
     */
    List<OrderBean> getDurationList();

    /**
     * 更新订单利润
     *
     * @param orderId
     * @param profit
     */
    void updateProfit(@Param("orderId") String orderId, @Param("profit") Double profit);

    /**
     * 获取订单列表
     *
     * @param pageIndex
     * @param pageSize
     * @return
     */
    List<OrderBean> getOrderList(@Param("pageIndex") Integer pageIndex,
                                 @Param("pageSize") Integer pageSize);

    /**
     * 获取订单列表数目
     *
     * @return
     */
    Integer countOrderList();
}
