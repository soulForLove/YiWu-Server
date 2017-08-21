package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.sec1.enums.IdleOrder;
import com.yiwu.changething.sec1.enums.OrderType;
import com.yiwu.changething.sec1.bean.IdleBean;
import com.yiwu.changething.sec1.model.IdleModel;
import com.yiwu.changething.sec1.model.IdleResModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public interface IdleMapper {

    /**
     * 获取商品列表
     *
     * @param idleResModel
     * @return
     */
    List<IdleBean> getIdleList(IdleResModel idleResModel);

    /**
     * 获取商品列表总数
     *
     * @param idleResModel
     * @return
     */
    Integer getIdleCount(IdleResModel idleResModel);

    /**
     * 根据id获取商品信息
     *
     * @param idleId
     * @return
     */
    IdleBean getIdleById(@Param("idleId") String idleId);

    /**
     * 新增商品信息
     *
     * @param idleModel
     */
    void insert(IdleModel idleModel);

    /**
     * 修改商品信息
     *
     * @param idleModel
     */
    void update(IdleModel idleModel);

    /**
     * 根据id删除商品信息
     *
     * @param idleId
     */
    void deleteById(@Param("idleId") String idleId);

    /**
     * 更新商品共享状态以及共享值
     *
     * @param share
     * @param shareValue
     * @param idleId
     */
    void updateShare(@Param("share") Boolean share, @Param("shareValue") Integer shareValue, @Param("idleId") String idleId);
}
