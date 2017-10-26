package com.yiwu.changething.sec1.bean;

import com.yiwu.changething.sec1.enums.OrderStatusType;
import com.yiwu.changething.sec1.enums.ShareStatus;
import lombok.Data;

import java.util.Date;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Data
public class ShareBean {

    private String id;

    private String userId;

    private String userName;

    private String idleId;

    private String idleName;

    private Date createTime;

    private Date updateTime;

    private Integer shareCycle;

    private Integer cycleNum;

    private Integer shareValue;

    private ShareStatus shareStatus;

}
