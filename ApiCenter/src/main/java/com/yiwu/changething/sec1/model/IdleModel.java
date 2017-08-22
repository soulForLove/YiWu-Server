package com.yiwu.changething.sec1.model;

import com.yiwu.changething.sec1.enums.ShareStatus;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class IdleModel {

    private String id;

    @NotNull
    private String name;//商品名称

    @NotNull
    private String typeId;//物品类别id

    private String description;//描述

    @NotNull
    private Double price;//单价

    @NumberFormat
    private Integer num;//数量

    private Date productionDate;//生产日期

    private Date guaranteeDate;//保质日期

    private String productionPlace;//产地

    @Max(100)
    private Integer extent;//新旧程度（0-100百分比）

    private Date createTime;//创建时间

    private Date updateTime;//更新时间
    @NotNull
    private String createBy;//创建者

    private String updateBy;//修改者

    private ShareStatus shareStatus;//是否共享

    private Integer shareValue;//共享值

    private Integer shareCycle;//共享周期

    public Integer getShareCycle() {
        return shareCycle;
    }

    public void setShareCycle(Integer shareCycle) {
        this.shareCycle = shareCycle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }

    public Date getGuaranteeDate() {
        return guaranteeDate;
    }

    public void setGuaranteeDate(Date guaranteeDate) {
        this.guaranteeDate = guaranteeDate;
    }

    public String getProductionPlace() {
        return productionPlace;
    }

    public void setProductionPlace(String productionPlace) {
        this.productionPlace = productionPlace;
    }

    public Integer getExtent() {
        return extent;
    }

    public void setExtent(Integer extent) {
        this.extent = extent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public ShareStatus getShareStatus() {
        return shareStatus;
    }

    public void setShareStatus(ShareStatus shareStatus) {
        this.shareStatus = shareStatus;
    }

    public Integer getShareValue() {
        return shareValue;
    }

    public void setShareValue(Integer shareValue) {
        this.shareValue = shareValue;
    }
}
