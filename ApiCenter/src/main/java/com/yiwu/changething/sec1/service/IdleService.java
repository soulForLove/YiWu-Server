package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.bean.ShareBean;
import com.yiwu.changething.sec1.enums.ShareStatus;
import com.yiwu.changething.sec1.exception.ErrorBuilder;
import com.yiwu.changething.sec1.exception.YwException;
import com.yiwu.changething.sec1.mapper.IdleMapper;
import com.yiwu.changething.sec1.bean.IdleBean;
import com.yiwu.changething.sec1.mapper.ShareMapper;
import com.yiwu.changething.sec1.model.IdleModel;
import com.yiwu.changething.sec1.model.IdleResModel;
import com.yiwu.changething.sec1.model.ShareModel;
import com.yiwu.changething.sec1.utils.Principal;
import com.yiwu.changething.sec1.utils.YwSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class IdleService {

    @Autowired
    private IdleMapper idleMapper;

    @Autowired
    private ShareMapper shareMapper;

    @Autowired
    private YwSecurityUtil ywSecurityUtil;

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
    public void insert(IdleModel idleModel, HttpServletRequest request) {
        Principal principal = ywSecurityUtil.checkUserLogin(request);
        idleModel.setId(UUID.randomUUID().toString());
        idleModel.setCreateBy(principal.getId());
        idleMapper.insert(idleModel);
    }

    /**
     * 修改商品信息
     *
     * @param idleModel
     */
    public void update(IdleModel idleModel, HttpServletRequest request) {
        checkIdleExist(idleModel.getId());
        Principal principal = ywSecurityUtil.checkUserLogin(request);
        idleModel.setUpdateBy(principal.getId());
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

    /**
     * 验证商品是否存在
     *
     * @param idleId
     */
    private void checkIdleExist(String idleId) {
        IdleBean idleModel = idleMapper.getIdleById(idleId);
        if (idleModel == null) {
            throw new YwException(ErrorBuilder.E101007);
        }
    }

    /**
     * 更新商品共享状态以及共享值、共享周期
     *
     * @param shareStatus
     * @param shareValue
     * @param idleId
     * @param shareCycle
     */
    public void updateShareStatus(ShareStatus shareStatus, Integer shareValue, String idleId, Integer shareCycle,
                                  HttpServletRequest request) {
        checkIdleExist(idleId);
        switch (shareStatus) {
            case LOCK:
                idleMapper.updateShareStatus(shareStatus, 0, idleId, shareCycle);
                ShareBean shareBean = shareMapper.getShareByIdleId(idleId);
                if (shareBean != null) {
                    ShareModel updateShare = new ShareModel();
                    updateShare.setShareStatus(ShareStatus.LOCK);//共享信息鎖定
                    updateShare.setId(shareBean.getId());
                    shareMapper.update(updateShare);
                }
                break;
            case NOTLOCK:
                idleMapper.updateShareStatus(shareStatus, shareValue, idleId, shareCycle);
                break;
        }
    }

}
