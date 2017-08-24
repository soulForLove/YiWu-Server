package com.yiwu.changething.sec1.service;

import com.yiwu.changething.sec1.model.ShareListModel;
import com.yiwu.changething.sec1.model.ShareModel;
import com.yiwu.changething.sec1.mapper.ShareMapper;
import com.yiwu.changething.sec1.bean.ShareBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class ShareService {

    @Autowired
    private ShareMapper shareMapper;

    /**
     * 新增订单信息
     *
     * @param shareModel
     */
    public void insert(ShareModel shareModel) {
        shareMapper.insert(shareModel);
    }

    /**
     * 根据id获取共享详细信息
     *
     * @param shareId
     * @return
     */
    public ShareBean getShareById(String shareId) {
        return shareMapper.getShareById(shareId);
    }

    /**
     * 根据商品id获取共享详细信息
     *
     * @param idleId
     * @return
     */
    public ShareBean getShareByIdleId(String idleId) {
        return shareMapper.getShareByIdleId(idleId);
    }

    /**
     * 获取共享列表
     *
     * @return
     */
    public List<ShareListModel> getShareList(Integer page, Integer pageSize) {
        Integer pageIndex = null;
        if (page != null && pageSize != null) {
            pageIndex = (page - 1) * pageSize;
        }
        return shareMapper.getShareList(pageIndex, pageSize);
    }

    /**
     * 获取共享列表數目
     *
     * @return
     */
    public Integer countShareList() {
        return shareMapper.countShareList();
    }
}
