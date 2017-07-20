/*
 * Copyright (C) 2015-2016  GenGee Technology Co. Ltd. All rights reserved.
 *
 *                              http://www.GenGee.cn
 *
 * IMPORTANT: Your use of this Software is limited to those specific rights
 * granted under the terms of a software license agreement between the user
 * who downloaded the software, his/her employer (which must be your employer)
 * and GenGee Technology Co. Ltd (the "License").  You may not use this
 * Software unless you agree to abide by the terms of the License. The License
 * limits your use, and you acknowledge, that the Software may not be modified,
 * copied or distributed unless embedded on a GenGee Technology intelligent
 * device or system. Other than for the foregoing purpose, you may not use,
 * reproduce, copy, prepare derivative works of, modify, distribute, perform,
 * display or sell this Software and/or its documentation for any purpose.
 *
 * YOU FURTHER ACKNOWLEDGE AND AGREE THAT THE SOFTWARE AND DOCUMENTATION ARE
 * PROVIDED "AS IS" WITHOUT WARRANTY OF ANY KIND, EITHER EXPRESS OR IMPLIED,
 * INCLUDING WITHOUT LIMITATION, ANY WARRANTY OF MERCHANTABILITY, TITLE,
 * NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE. IN NO EVENT SHALL
 * GENGEE TECHNOLOGY CO. LTD OR ITS LICENSORS BE LIABLE OR OBLIGATED UNDER
 * CONTRACT, NEGLIGENCE, STRICT LIABILITY, CONTRIBUTION, BREACH OF WARRANTY,
 * OR OTHER LEGAL EQUITABLE THEORY ANY DIRECT OR INDIRECT DAMAGES OR EXPENSES
 * INCLUDING BUT NOT LIMITED TO ANY INCIDENTAL, SPECIAL, INDIRECT, PUNITIVE
 * OR CONSEQUENTIAL DAMAGES, LOST PROFITS OR LOST DATA, COST OF PROCUREMENT
 * OF SUBSTITUTE GOODS, TECHNOLOGY, SERVICES, OR ANY CLAIMS BY THIRD PARTIES
 *   (INCLUDING BUT NOT LIMITED TO ANY DEFENSE THEREOF), OR OTHER SIMILAR COSTS.
 *
 * Should you have any questions regarding your right to use this Software,
 * contact GenGee Technology Co. Ltd at www.GenGee.cn.
 */

package com.yiwu.changething.sec1.exception;

import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@ControllerAdvice
public class YwExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Qualifier("errorMessage")
    @Autowired
    private ReloadableResourceBundleMessageSource errorMessage;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RespModel system(Exception e) {
        LOGGER.error("System Error Exception:", e);
        return getRespModel(101001);
    }

    @ExceptionHandler(YwException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RespModel error(YwException e) {
        LOGGER.warn("WARN Exception:{}", e.getErrorCode());
        return getRespModel(e.getErrorCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RespModel argumentError(MethodArgumentNotValidException e) {
        LOGGER.error("Exception:", e);
        return getRespModel(101005);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public RespModel argumentError(MissingServletRequestParameterException e) {
        LOGGER.error("Exception:", e);
        return getRespModel(101005);
    }

    @ExceptionHandler(YwShiroException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public RespModel unauthorized(YwShiroException e) {
        Integer errorCode = e.getErrorCode();
        LOGGER.trace("WARN Exception:用户未授权");
        return getRespModel(errorCode);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public RespModel unauthorized(UnauthorizedException e) {
        LOGGER.trace("WARN Exception:用户未授权");
        return getRespModel(101004);
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public RespModel badRequest(HttpMediaTypeException e) {
        LOGGER.debug("Bad Request Exception:{}", e.getMessage());
        return getRespModel(101005);
    }

  /*
   @ExceptionHandler(AuthenException.class)
    @ResponseBody
    public RespModel loginFailure(AuthenException e) {
        LOGGER.debug("Bad Request Exception:{}", e.getMessage());
        return getRespModel(101003);
    }

    @ExceptionHandler(Oauth2Exception.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public RespModel unauthorized(Oauth2Exception e) {
        LOGGER.debug("Bad Request Exception:{}", e.getMessage());
        return getRespModel(101004);
    }


    @ExceptionHandler(UploadException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RespModel shiroException(UploadException e) {
        LOGGER.error("七牛文件上传错误", e);
        return getRespModel(101008);
    }
    */

    private RespModel getRespModel(Integer code) {
        ErrorModel errorModel = new ErrorModel(code, this.getErrorMsg(code.toString()));
        RespModel respModel = new RespModel();
        respModel.setSuccess(false);
        respModel.setRemark("请求失败");
        respModel.setDetails(errorModel);
        return respModel;
    }

    private RespModel getRespModel(Integer code, Locale locale) {
        ErrorModel errorModel = new ErrorModel(code, this.getErrorMsg(code.toString(), locale));
        RespModel respModel = new RespModel();
        respModel.setSuccess(false);
        respModel.setRemark("请求失败");
        respModel.setDetails(errorModel);
        return respModel;
    }

    private String getErrorMsg(String code) {
        return this.errorMessage.getMessage(code, null, Locale.CHINA);
    }

    private String getErrorMsg(String code, Locale locale) {
        return this.errorMessage.getMessage(code, null, locale);
    }

}
