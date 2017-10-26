package com.yiwu.changething.sec1.interceptor;

import com.yiwu.changething.sec1.bean.WeChatInfo;
import com.yiwu.changething.sec1.exception.AuthenticationException;
import com.yiwu.changething.sec1.exception.ErrorBuilder;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.utils.Cache;
import com.yiwu.changething.sec1.utils.ContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 */
@Component
public class ControllerInterceptor implements HandlerInterceptor {

    @Autowired
    private Cache cache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = request.getHeader("userId");
        String token = request.getHeader("token");

        if (StringUtils.isBlank(token) || StringUtils.isBlank(userId)) {
            throw new YwException(ErrorBuilder.E101001);
        }

        String sysToken = cache.getToken(userId);

        if (token.equals(sysToken)) {// 正式入口
            WeChatInfo user = cache.getUser(userId);
            ContextHolder.bind(user);
            return true;
        } else {
            throw new AuthenticationException("无效的授权令牌");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {
        ContextHolder.unbind();
    }

}
