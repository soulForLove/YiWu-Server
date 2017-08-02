package com.yiwu.changething.sec1.model;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class AuthModel {

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
