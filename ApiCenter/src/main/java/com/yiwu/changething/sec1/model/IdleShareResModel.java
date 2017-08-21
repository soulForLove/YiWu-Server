package com.yiwu.changething.sec1.model;

import javax.validation.constraints.NotNull;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class IdleShareResModel {

    @NotNull
    private Boolean share;//是否共享

    private Integer shareValue;//共享值

    private Integer shareCycle;//共享周期

    @NotNull
    private String idleId;//商品id

    public Integer getShareCycle() {
        return shareCycle;
    }

    public void setShareCycle(Integer shareCycle) {
        this.shareCycle = shareCycle;
    }

    public Boolean getShare() {
        return share;
    }

    public void setShare(Boolean share) {
        this.share = share;
    }

    public Integer getShareValue() {
        return shareValue;
    }

    public void setShareValue(Integer shareValue) {
        this.shareValue = shareValue;
    }

    public String getIdleId() {
        return idleId;
    }

    public void setIdleId(String idleId) {
        this.idleId = idleId;
    }
}
