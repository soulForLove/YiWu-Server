package com.yiwu.changething.sec1.filter;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class UserToken implements AuthenticationToken {

    private String resourceId;
    private String userId;

    public UserToken(String resourceId, String idleId) {
        this.resourceId = resourceId;
        this.userId = idleId;
    }

    public Object getPrincipal() {
        return this.resourceId;
    }

    public Object getCredentials() {
        return this.userId;
    }

    @Override
    public String toString() {
        return "UserToken{" +
                "resourceId='" + resourceId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
