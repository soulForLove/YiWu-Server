package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.sec1.bean.ShareBean;
import com.yiwu.changething.sec1.model.PageModel;
import com.yiwu.changething.sec1.model.ShareResModel;
import com.yiwu.changething.sec1.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@RestController
@RequestMapping("/share")
public class ShareController {

    @Autowired
    private ShareService shareService;

    /**
     * 根据id获取共享信息
     *
     * @param shareId
     * @return
     */
    @GetMapping("/{shareId}")
    public ShareBean getShareById(@PathVariable("shareId") String shareId) {
        return shareService.getShareById(shareId);
    }

    /**
     * 根据idleId获取共享信息
     *
     * @param idleId
     * @return
     */
    @GetMapping("/idleId/{idleId}")
    public ShareBean getShareByIdleId(@PathVariable("idleId") String idleId) {
        return shareService.getShareByIdleId(idleId);
    }

    /**
     * 获取共享信息列表
     *
     * @return
     */
    @GetMapping("/list")
    public Map<String, Object> getShareList(ShareResModel shareResModel) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("shareList", shareService.getShareList(shareResModel));
        resultMap.put("totalCount", shareService.countShareList(shareResModel));
        return resultMap;
    }

}
