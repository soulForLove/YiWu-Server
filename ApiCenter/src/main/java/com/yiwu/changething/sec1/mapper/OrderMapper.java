package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.sec1.bean.OrderBean;
import com.yiwu.changething.sec1.enums.OrderStatusType;
import com.yiwu.changething.sec1.model.OrderModel;
import org.apache.ibatis.annotations.Param;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public interface OrderMapper {

    OrderModel getOrderById(@Param("orderId") String orderId);

    void insert(OrderBean orderBean);

    void update(OrderBean orderBean);

    void deleteById(@Param("orderId") String orderId);

    void updateStatus(@Param("orderId") String orderId, @Param("status") OrderStatusType status);
}
