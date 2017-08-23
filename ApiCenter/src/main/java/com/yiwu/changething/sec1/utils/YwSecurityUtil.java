package com.yiwu.changething.sec1.utils;

import com.yiwu.changething.sec1.exception.ErrorBuilder;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.service.SystemVariableService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Component
public class YwSecurityUtil extends SecurityUtils {

    /**
     * 获取当前用户的principal,验证用户是否登录
     *
     * @param request
     */
    public Principal checkUserLogin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Principal principal = (Principal) session.getAttribute(SystemVariableService.USER_INFO);
        if (principal == null) {
            throw new YwException(ErrorBuilder.E101002);
        }
        return principal;
    }
}
