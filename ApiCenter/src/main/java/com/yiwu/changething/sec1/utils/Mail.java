package com.yiwu.changething.sec1.utils;

import java.io.Serializable;

/**
 * 邮件实体类
 *
 * @author Lim Lin
 * @created 2016-10-26 上午11:33:31
 */
public class Mail implements Serializable {

    private String host; // 服务器地址

    private String sender; // 发件人的邮箱

    private String receiver; // 收件人的邮箱

    private String senderName; // 发件人昵称

    private String userName; // 账号

    private String password; // 密码

    private String subject; // 主题

    private String message; // 信息(支持HTML)

    private String copy;//抄送

    private String secretCopy;//秘密抄送

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCopy() {
        return copy;
    }

    public void setCopy(String copy) {
        this.copy = copy;
    }

    public String getSecretCopy() {
        return secretCopy;
    }

    public void setSecretCopy(String secretCopy) {
        this.secretCopy = secretCopy;
    }
}
