package com.yiwu.changething.sec1.bean;

import lombok.Data;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Data
public class UserBean {

    private String id;

    private String name;

    private String password;

    private String phone;

    private String salt;//混淆盐

    private String avatar;//头像

    private String email;//邮箱

    private Integer shareValue;//共享值

}
