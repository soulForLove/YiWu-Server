package com.yiwu.changething.sec1.enums;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public enum IdleOrder {

    PRICE("price", "单价"),

    EXTENT("extent", "新旧程度");

    private String chineseName;

    private String englishName;

    IdleOrder(String englishName, String chineseName) {
        this.chineseName = chineseName;
        this.englishName = englishName;
    }

    public String getChineseName() {
        return chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }
}
