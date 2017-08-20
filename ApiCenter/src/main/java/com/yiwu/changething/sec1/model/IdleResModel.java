package com.yiwu.changething.sec1.model;

import com.yiwu.changething.sec1.enums.IdleOrder;
import com.yiwu.changething.sec1.enums.OrderType;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class IdleResModel extends PageModel {

    private String name;

    private String typeId;//類別id

    private Double minPrice;//最低價格

    private Double maxPrice;//最高價格

    private String productionPlace;//產地

    private Boolean share;//是否共享

    private Integer minShareValue;//最低共享值

    private Integer maxShareValue;//最高共享值

    private IdleOrder idleOrder;

    private OrderType orderType;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public Boolean getShare() {
        return share;
    }

    public void setShare(Boolean share) {
        this.share = share;
    }

    public Integer getMinShareValue() {
        return minShareValue;
    }

    public void setMinShareValue(Integer minShareValue) {
        this.minShareValue = minShareValue;
    }

    public Integer getMaxShareValue() {
        return maxShareValue;
    }

    public void setMaxShareValue(Integer maxShareValue) {
        this.maxShareValue = maxShareValue;
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
