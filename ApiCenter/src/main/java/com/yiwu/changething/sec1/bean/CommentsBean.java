package com.yiwu.changething.sec1.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Data
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
}
