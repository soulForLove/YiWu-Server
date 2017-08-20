package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.sec1.bean.UserBean;
import org.apache.ibatis.annotations.Param;


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
}
