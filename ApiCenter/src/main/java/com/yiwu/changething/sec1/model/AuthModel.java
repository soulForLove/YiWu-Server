package com.yiwu.changething.sec1.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class AuthModel {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
