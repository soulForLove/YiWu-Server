package com.yiwu.changething.sec1.enums;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public enum OrderStatusType {
    NOTPAY("notPay", "未付款"),

    COMPLETED("completed", "已完成");

    private String chineseName;

    private String englishName;

    OrderStatusType(String englishName, String chineseName) {
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
