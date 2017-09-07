package com.yiwu.changething.sec1.exception;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class YwException extends RuntimeException {

    private ErrorModel errorModel;

    public YwException(ErrorModel errorModel) {
        this.errorModel = errorModel;
    }

    public ErrorModel getErrorModel() {
        return errorModel;
    }
}
