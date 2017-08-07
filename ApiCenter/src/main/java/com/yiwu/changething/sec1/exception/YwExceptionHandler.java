package com.yiwu.changething.sec1.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.yiwu.changething.sec1.exception.ErrorBuilder.*;

/**
 * @author Maxwell <huangxiongyi@gengee.cn>
 */
@ControllerAdvice
public class YwExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RespModel system(Exception e) {
        LOGGER.error("System Error Exception:", e);
        return getRespModel(E101001);
    }

    @ExceptionHandler(YwException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RespModel error(YwException e) {
        ErrorModel errorModel = e.getErrorModel();
        return this.getRespModel(errorModel);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RespModel argumentError(MethodArgumentNotValidException e) {
        LOGGER.error("Exception:", e);
        return getRespModel(E101005);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RespModel argumentError(MissingServletRequestParameterException e) {
        LOGGER.error("Exception:", e);
        return getRespModel(E101005);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public RespModel unauthorized(UnauthorizedException e) {
        LOGGER.trace("WARN Exception:用户未授权");
        return getRespModel(E101004);
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RespModel badRequest(HttpMediaTypeException e) {
        LOGGER.debug("Bad Request Exception:{}", e.getMessage());
        return getRespModel(E101005);
    }

    /*
    @ExceptionHandler(AuthenException.class)
    @ResponseBody
    public RespModel loginFailure(AuthenException e) {
        LOGGER.debug("Bad Request Exception:{}", e.getMessage());
        return getRespModel(E101003);
    }

    @ExceptionHandler(Oauth2Exception.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public RespModel unauthorized(Oauth2Exception e) {
        LOGGER.debug("Bad Request Exception:{}", e.getMessage());
        return getRespModel(E101003);
    }
    */


    private RespModel getRespModel(ErrorModel errorModel) {
        RespModel respModel = new RespModel();
        respModel.setSuccess(false);
        respModel.setRemark("请求失败");
        respModel.setDetails(errorModel);
        return respModel;
    }
}
