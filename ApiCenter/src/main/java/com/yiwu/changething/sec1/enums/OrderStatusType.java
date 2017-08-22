package com.yiwu.changething.sec1.enums;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public enum OrderStatusType {
    NOTPAY("notPay", "未付款"),

    PAY("pay", "已付款"),

    RENEW("renew", "续费");

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
