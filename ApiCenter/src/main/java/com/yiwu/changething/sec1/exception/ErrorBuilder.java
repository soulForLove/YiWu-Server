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

/**
 * Created by Maxwell <huangxiongyi@gengee.cn>
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
