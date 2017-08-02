package com.yiwu.changething.sec1.bean;


import java.io.Serializable;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class Principal implements Serializable {

    private String id;

    private String phone;

    private String name;

    private String avatar;

    private String email;

    public Principal(String id, String phone, String name, String avatar, String email) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

}
