package com.yiwu.changething.sec1.filter;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class IdleToken implements AuthenticationToken {

    private String resourceId;
    private String idleId;

    public IdleToken(String resourceId, String idleId) {
        this.resourceId = resourceId;
        this.idleId = idleId;
    }

    public Object getPrincipal() {
        return this.resourceId;
    }

    public Object getCredentials() {
        return this.idleId;
    }

    @Override
    public String toString() {
        return "IdleToken{" +
                "resourceId='" + resourceId + '\'' +
                ", idleId='" + idleId + '\'' +
                '}';
    }
}
