package com.yiwu.changething.sec1.bean;

import lombok.Data;

import java.util.Date;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Data
public class WeChatInfo {

    private String userId;

    private String openId;

    private String mobile;

    private Integer gender;

    private String nickName;

    private String avatarUrl;

    private Date createTime;
}
