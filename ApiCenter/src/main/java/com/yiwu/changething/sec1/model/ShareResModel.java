package com.yiwu.changething.sec1.model;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class ShareResModel extends PageModel {

    private String name;

    private Integer minShareValue;

    private Integer maxShareValue;

    private Integer shareCycle;

    private String typeId;//商品类别

    private Integer extent;//新旧程度（0-100）

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinShareValue() {
        return minShareValue;
    }

    public void setMinShareValue(Integer minShareValue) {
        this.minShareValue = minShareValue;
    }

    public Integer getMaxShareValue() {
        return maxShareValue;
    }

    public void setMaxShareValue(Integer maxShareValue) {
        this.maxShareValue = maxShareValue;
    }

    public Integer getShareCycle() {
        return shareCycle;
    }

    public void setShareCycle(Integer shareCycle) {
        this.shareCycle = shareCycle;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public Integer getExtent() {
        return extent;
    }

    public void setExtent(Integer extent) {
        this.extent = extent;
    }
}
