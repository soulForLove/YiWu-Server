package com.yiwu.changething.sec1.bean;

import java.util.Date;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class OrderBean {

    private String id;

    private String userId;

    private String idleId;

    private Date createTime;

    private Date updateTime;

    private Integer shareValue;

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

    public Integer getShareValue() {
        return shareValue;
    }

    public void setShareValue(Integer shareValue) {
        this.shareValue = shareValue;
    }
}
