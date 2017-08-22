package com.yiwu.changething.sec1.model;

import com.yiwu.changething.sec1.enums.OrderStatusType;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class ShareModel {

    private String id;

    @NotNull
    private String userId;

    @NotNull
    private String idleId;

    private Date createTime;

    private Date updateTime;

    @NotNull
    private Integer value;

    @NotNull
    private Integer cycle;

    private Integer num;

    private OrderStatusType status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdleId() {
        return idleId;
    }

    public void setIdleId(String idleId) {
        this.idleId = idleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public OrderStatusType getStatus() {
        return status;
    }

    public void setStatus(OrderStatusType status) {
        this.status = status;
    }
}
