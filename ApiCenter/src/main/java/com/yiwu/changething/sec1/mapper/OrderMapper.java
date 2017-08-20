package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.sec1.bean.OrderBean;
import com.yiwu.changething.sec1.model.OrderModel;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public interface OrderMapper {

    OrderModel getOrderById(String orderId);

    void insert(OrderBean orderBean);

    void update(OrderBean orderBean);

    void deleteById(String orderId);
}
