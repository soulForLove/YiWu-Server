package com.yiwu.changething.sec1.bean;

import com.yiwu.changething.sec1.enums.OrderStatusType;
import com.yiwu.changething.sec1.enums.ShareStatus;

import java.util.Date;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class ShareBean {

    private String id;

    private String userId;

    private String userName;

    private String idleId;

    private String idleName;

    private Date createTime;

    private Date updateTime;

    private Integer cycle;

    private Integer num;

    private Integer value;

    private ShareStatus status;

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

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public ShareStatus getStatus() {
        return status;
    }

    public void setStatus(ShareStatus status) {
        this.status = status;
    }
}
