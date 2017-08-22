package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.sec1.model.OrderModel;
import com.yiwu.changething.sec1.enums.OrderStatusType;
import com.yiwu.changething.sec1.bean.OrderBean;
import org.apache.ibatis.annotations.Param;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public interface OrderMapper {

    OrderBean getOrderById(@Param("orderId") String orderId);

    void insert(OrderModel orderBean);

    void update(OrderModel orderBean);

    void deleteById(@Param("orderId") String orderId);

    void updateStatus(@Param("orderId") String orderId, @Param("status") OrderStatusType status);

    Integer getOrderCountByIdleId(@Param("idleId") String idleId);
}
