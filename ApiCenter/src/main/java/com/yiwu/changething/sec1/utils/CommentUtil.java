package com.yiwu.changething.sec1.utils;


import com.yiwu.changething.sec1.bean.Principal;
import com.yiwu.changething.sec1.exception.YwException;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

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
    public Principal getCurrentPrincipal() {
        Principal principal = (Principal) SecurityUtils.getSubject().getPrincipal();
        if (null == principal) {
            throw new YwException(101002);
        }
        return principal;
    }
}
