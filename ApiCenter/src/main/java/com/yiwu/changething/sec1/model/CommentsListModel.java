package com.yiwu.changething.sec1.model;

import java.util.Date;
import java.util.List;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class CommentsListModel {

    private String id;

    private String parentId;

    private String idleId;

    private String userId;

    private String userName;

    private String replyTo;//回复谁

    private String replyName;

    private String detail;//内容

    private Date createTime;

    private List<CommentsModel> childComments;

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
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

    public List<CommentsModel> getChildComments() {
        return childComments;
    }

    public void setChildComments(List<CommentsModel> childComments) {
        this.childComments = childComments;
    }
}
