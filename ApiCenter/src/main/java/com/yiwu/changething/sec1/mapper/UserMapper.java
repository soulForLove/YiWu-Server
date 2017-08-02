package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.sec1.bean.User;
import org.apache.ibatis.annotations.Param;


/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public interface UserMapper {
    /**
     * get user
     *
     * @param name
     * @return
     */
    User getUser(@Param("name") String name);

    /**
     * add user
     *
     * @param user
     */
    void insert(User user);
}
