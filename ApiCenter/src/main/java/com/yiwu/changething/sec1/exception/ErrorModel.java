package com.yiwu.changething.sec1.exception;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class ErrorModel {

    private Integer code;

    private String description;

    public ErrorModel(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
