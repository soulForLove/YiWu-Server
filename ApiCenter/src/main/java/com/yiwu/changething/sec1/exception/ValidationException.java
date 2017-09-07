package com.yiwu.changething.sec1.exception;

import org.apache.shiro.authc.AccountException;

/**
 * 校验失败异常
 *
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class ValidationException extends AccountException {
    public ValidationException() {
        super();
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
