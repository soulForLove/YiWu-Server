package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.enums.IdleOrder;
import com.yiwu.changething.sec1.enums.OrderType;
import com.yiwu.changething.sec1.mapper.IdleMapper;
import com.yiwu.changething.sec1.model.IdleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class IdleService {

    @Autowired
    private IdleMapper idleMapper;

    /**
     * 获取商品列表
     *
     * @param name
     * @param idleOrder
     * @param orderType
     * @param page
     * @param pageSize
     * @return
     */
    public List<IdleModel> getIdleList(String name, IdleOrder idleOrder, OrderType orderType, Integer page,
                                       Integer pageSize) {
        Integer pageIndex = null;
        if (page != null && pageSize != null) {
            pageIndex = (page - 1) * pageSize;
        }
        return idleMapper.getIdleList(name, idleOrder, orderType, pageIndex, pageSize);
    }

    /**
     * 获取商品列表总数
     *
     * @param name
     * @return
     */
    public Integer getIdleCount(String name) {
        return idleMapper.getIdleCount(name);
    }

    /**
     * 新增商品信息
     *
     * @param idleModel
     */
    public void insert(IdleModel idleModel) {
        String id = UUID.randomUUID().toString();
        idleModel.setId(id);
        idleMapper.insert(idleModel);
    }

    /**
     * 修改商品信息
     *
     * @param idleModel
     */
    public void update(IdleModel idleModel) {
        idleMapper.update(idleModel);
    }

    /**
     * 根据id删除商品信息
     *
     * @param idleId
     */
    public void deleteById(String idleId) {
        idleMapper.deleteById(idleId);
    }
}
