package com.yiwu.changething.sec1.bean;

import com.yiwu.changething.sec1.enums.OrderStatusType;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class OrderBean {

    private String id;

    private String userId;

    private String userName;

    private String idleId;

    private String idleName;

    private Date createTime;

    private Date updateTime;

    private Integer shareCycle;

    private Integer cycleNum;

    private Integer shareValue;

    private OrderStatusType status;

    public Integer getShareCycle() {
        return shareCycle;
    }

    public void setShareCycle(Integer shareCycle) {
        this.shareCycle = shareCycle;
    }

    public Integer getCycleNum() {
        return cycleNum;
    }

    public void setCycleNum(Integer cycleNum) {
        this.cycleNum = cycleNum;
    }

    public OrderStatusType getStatus() {
        return status;
    }

    public void setStatus(OrderStatusType status) {
        this.status = status;
    }

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdleId() {
        return idleId;
    }

    public void setIdleId(String idleId) {
        this.idleId = idleId;
    }

    public String getIdleName() {
        return idleName;
    }

    public void setIdleName(String idleName) {
        this.idleName = idleName;
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

    public Integer getShareValue() {
        return shareValue;
    }

    public void setShareValue(Integer shareValue) {
        this.shareValue = shareValue;
    }
}