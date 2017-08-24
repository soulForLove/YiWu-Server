package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.sec1.model.ShareListModel;
import com.yiwu.changething.sec1.model.ShareModel;
import com.yiwu.changething.sec1.bean.ShareBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public interface ShareMapper {
    /**
     * 新增共享信息
     *
     * @param shareModel
     */
    void insert(ShareModel shareModel);

    /**
     * 更新共享信息
     *
     * @param shareModel
     */
    void update(ShareModel shareModel);

    /**
     * 根據id獲取共享信息
     *
     * @param shareId
     * @return
     */
    ShareBean getShareById(@Param("shareId") String shareId);

    /**
     * 根據商品id獲取共享信息（一個商品i對應一條共享信息）
     *
     * @param idleId
     * @return
     */
    ShareBean getShareByIdleId(@Param("idleId") String idleId);

    /**
     * 獲取共享列表
     *
     * @return
     */
    List<ShareListModel> getShareList(@Param("pageIndex") Integer pageIndex,
                                      @Param("pageSize") Integer pageSize);

    /**
     * 獲取共享列表數目
     *
     * @return
     */
    Integer countShareList();

}
