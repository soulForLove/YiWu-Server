package com.yiwu.changething.sec1.bean;

import lombok.Data;

/**
 * @author linwei
 * @version 1.0
 * @data 2017年5月28日
 * @描述:
 */
@Data
public class LoginResp {

    private String userId;
    private String token;

    public LoginResp(String userId, String token) {
        this.userId = userId;
        this.token = token;
    }
}
