package com.yiwu.changething.sec1.model;

import com.yiwu.changething.sec1.enums.IdleOrder;
import com.yiwu.changething.sec1.enums.OrderType;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class IdleResModel extends PageModel {
    private String name;
    private IdleOrder idleOrder;
    private OrderType orderType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IdleOrder getIdleOrder() {
        return idleOrder;
    }

    public void setIdleOrder(IdleOrder idleOrder) {
        this.idleOrder = idleOrder;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
}
