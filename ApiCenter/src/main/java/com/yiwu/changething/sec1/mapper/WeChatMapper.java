package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.sec1.bean.WeChatInfo;
import org.apache.ibatis.annotations.Param;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public interface WeChatMapper {

    WeChatInfo getByOpenId(@Param("openId") String openId);

    void insert(WeChatInfo weChatInfo);

    void update(WeChatInfo weChatInfo);

    WeChatInfo getById(@Param("userId") String userId);
}
