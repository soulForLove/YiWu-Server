package com.yiwu.changething.sec1.bean;

import com.yiwu.changething.sec1.enums.OrderStatusType;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Data
public class OrderBean {

    private String id;

    private String userId;

    private String userName;

    private String idleId;

    private String idleName;

    private Date createTime;

    private Date updateTime;

    private Integer shareCycle;

    private Integer cycleNum;

    private Integer duration;

    private Integer shareValue;

    private OrderStatusType status;

    private Double profit;//订单利润

}
