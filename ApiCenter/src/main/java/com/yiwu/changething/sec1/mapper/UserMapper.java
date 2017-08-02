package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.sec1.bean.User;
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
    User getByName(@Param("name") String name);

    /**
     * add user
     *
     * @param user
     */
    void insert(User user);

    /**
     * get user by email
     *
     * @param email
     * @return
     */
    User getByEmail(@Param("email") String email);

    /**
     * 修改密码
     *
     * @param user
     */
    void updatePassword(User user);
}
