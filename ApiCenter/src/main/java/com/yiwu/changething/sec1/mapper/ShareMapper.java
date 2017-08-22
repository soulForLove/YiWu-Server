package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.sec1.model.ShareModel;
import com.yiwu.changething.sec1.enums.ShareStatus;
import com.yiwu.changething.sec1.bean.ShareBean;
import org.apache.ibatis.annotations.Param;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public interface ShareMapper {

    void insert(ShareModel shareModel);

    void update(ShareModel shareModel);

    ShareBean getShareById(@Param("shareId") String shareId);

    ShareBean getShareByIdleId(@Param("idleId") String idleId);

    void updateStatus(@Param("shareId") String shareId, @Param("status") ShareStatus status);
}
