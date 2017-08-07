package com.yiwu.changething.sec1.utils;


import com.yiwu.changething.sec1.bean.Principal;
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
public class CommentUtil {

    /**
     * 获取当前用户的principal
     *
     * @return
     */
    public Principal getCurrentPrincipal(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Principal principal = (Principal) session.getAttribute(SystemVariableService.USER_INFO);
        if (null == principal) {
            throw new YwException(ErrorBuilder.E101002);
        }
        return principal;
    }
}
