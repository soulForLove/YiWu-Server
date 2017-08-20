package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.enums.IdleOrder;
import com.yiwu.changething.sec1.enums.OrderType;
import com.yiwu.changething.sec1.exception.ErrorBuilder;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.mapper.IdleMapper;
import com.yiwu.changething.sec1.bean.IdleBean;
import com.yiwu.changething.sec1.model.IdleModel;
import com.yiwu.changething.sec1.model.IdleResModel;
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
     * @param idleResModel
     * @return
     */
    public List<IdleBean> getIdleList(IdleResModel idleResModel) {

        if (idleResModel.getPage() != null && idleResModel.getPageSize() != null) {
            idleResModel.setPage((idleResModel.getPage() - 1) * idleResModel.getPageSize());
        }
        return idleMapper.getIdleList(idleResModel);
    }

    /**
     * 获取商品列表总数
     *
     * @param idleResModel
     * @return
     */
    public Integer getIdleCount(IdleResModel idleResModel) {
        return idleMapper.getIdleCount(idleResModel);
    }

    /**
     * 新增商品信息
     *
     * @param idleModel
     */
    public void insert(IdleModel idleModel) {
        idleModel.setId(UUID.randomUUID().toString());
        idleMapper.insert(idleModel);
    }

    /**
     * 修改商品信息
     *
     * @param idleModel
     */
    public void update(IdleModel idleModel) {
        IdleBean idle = idleMapper.getIdleById(idleModel.getId());
        if (idle == null) {
            throw new YwException(ErrorBuilder.E101007);
        }
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

    /**
     * 根据id获取商品信息
     *
     * @param idleId
     * @return
     */
    public IdleBean getIdleById(String idleId) {
        IdleBean idleModel = idleMapper.getIdleById(idleId);
        if (idleModel == null) {
            throw new YwException(ErrorBuilder.E101007);
        }
        return idleModel;
    }
}
