package com.yiwu.changething.sec1.bean;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class CommentsBean {

    private String id;

    private String parentId;

    @NotNull
    private String idleId;

    @NotNull
    private String userId;

    private String replyTo;//回复谁

    @NotNull
    private String detail;//内容

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getIdleId() {
        return idleId;
    }

    public void setIdleId(String idleId) {
        this.idleId = idleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
