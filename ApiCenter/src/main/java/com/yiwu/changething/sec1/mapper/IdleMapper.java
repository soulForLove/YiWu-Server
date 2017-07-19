package com.yiwu.changething.sec1.mapper;

import com.yiwu.changething.sec1.enums.IdleOrder;
import com.yiwu.changething.sec1.enums.OrderType;
import com.yiwu.changething.sec1.model.IdleModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public interface IdleMapper {

    /**
     * 获取商品列表
     *
     * @param name
     * @param idleOrder
     * @param orderType
     * @return
     */
    List<IdleModel> getIdleList(@Param("name") String name,
                                @Param("IdleOrder") IdleOrder idleOrder,
                                @Param("orderType") OrderType orderType,
                                @Param("pageIndex") Integer pageIndex,
                                @Param("pageSize") Integer pageSize);

    /**
     * 获取商品列表总数
     *
     * @param name
     * @return
     */
    Integer getIdleCount(@Param("name") String name);
}
