package com.yiwu.changething.sec1.exception;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class ErrorBuilder {

    // 系统错误
    public static final ErrorModel E101000 = new ErrorModel(101000, "未知错误");

    public static final ErrorModel E101001 = new ErrorModel(101001, "系统错误");

    public static final ErrorModel E101002 = new ErrorModel(101002, "用户未登录");

    public static final ErrorModel E101003 = new ErrorModel(101003, "用户名或密码错误，请重新输入");

    public static final ErrorModel E101004 = new ErrorModel(101004, "没有权限访问该资源");

    public static final ErrorModel E101005 = new ErrorModel(101005, "参数错误");

    public static final ErrorModel E101006 = new ErrorModel(101006, "未知用户");

    public static final ErrorModel E101007 = new ErrorModel(101007, "不存在该商品");

    public static final ErrorModel E101008 = new ErrorModel(101008, "用户名已存在");

    public static final ErrorModel E101009 = new ErrorModel(101009, "邮箱已注册");

    public static final ErrorModel E101010 = new ErrorModel(101010, "该订单不存在");

    public static final ErrorModel E101011 = new ErrorModel(101011, "买家共享值不足");

    public static final ErrorModel E101012 = new ErrorModel(101012, "该用户不存在");
}
