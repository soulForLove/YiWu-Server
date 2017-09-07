package com.yiwu.changething.sec1.exception;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class RespModel {

    private Boolean success = true;

    private String remark = "请求成功";

    private Object details;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Object getDetails() {
        return details;
    }

    public void setDetails(Object details) {
        this.details = details;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
