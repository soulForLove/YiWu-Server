package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.sec1.bean.User;
import org.apache.ibatis.annotations.Param;


/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public interface UserMapper {

    User getUser(@Param("name") String name);


}
