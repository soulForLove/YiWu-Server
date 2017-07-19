package com.yiwu.changething.sec1.enums;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public enum OrderType {
    DESC("desc", "降序"),

    ASC("asc", "升序");

    private String chineseName;

    private String englishName;

    OrderType(String englishName, String chineseName) {
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
