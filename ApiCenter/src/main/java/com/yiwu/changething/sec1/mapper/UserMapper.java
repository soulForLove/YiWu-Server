package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.sec1.bean.UserBean;
import org.apache.ibatis.annotations.Param;

import javax.swing.*;


/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public interface UserMapper {
    /**
     * get user by name
     *
     * @param name
     * @return
     */
    UserBean getByName(@Param("name") String name);

    /**
     * add user
     *
     * @param user
     */
    void insert(UserBean user);

    /**
     * get user by email
     *
     * @param email
     * @return
     */
    UserBean getByEmail(@Param("email") String email);

    /**
     * 修改密码
     *
     * @param user
     */
    void updatePassword(UserBean user);

    /**
     * 获取用户共享值
     *
     * @param userId
     * @return
     */
    Integer getShareValue(@Param("userId") String userId);

    /**
     * 更新用户共享值
     *
     * @param shareValue
     * @param userId
     */
    void updateShareValue(@Param("shareValue") Integer shareValue, @Param("userId") String userId);

    /**
     * 获取用户
     *
     * @param userId
     * @return
     */
    UserBean getById(@Param("userId") String userId);
}
