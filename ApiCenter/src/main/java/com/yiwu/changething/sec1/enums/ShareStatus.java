package com.yiwu.changething.sec1.enums;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public enum ShareStatus {

    LOCK("lock", "不可共享"),

    NOTLOCK("not lock", "可共享");

    private String chineseName;

    private String englishName;

    ShareStatus(String englishName, String chineseName) {
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
