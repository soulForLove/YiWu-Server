package com.yiwu.changething.sec1.bean;

import com.yiwu.changething.sec1.enums.ShareStatus;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Data
public class IdleBean {

    private String id;

    private String name;//商品名称

    private String typeId;//物品类别id

    private String description;//描述

    private Double price;//单价

    private Integer num;//数量

    private Date productionDate;//生产日期

    private Date guaranteeDate;//保质日期

    private String productionPlace;//产地

    private Integer extent;//新旧程度（0-100百分比）

    private Date createTime;//创建时间

    private Date updateTime;//更新时间

    private String createBy;//创建者

    private String updateBy;//修改者

    private ShareStatus shareStatus;//是否共享

    private Integer shareValue;//共享值

    private Integer shareCycle;//共享周期

    private String thumbnail;//缩略图

}
