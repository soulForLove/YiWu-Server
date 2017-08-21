package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.common.service.CommonService;
import com.yiwu.changething.sec1.bean.IdleBean;
import com.yiwu.changething.sec1.model.IdleModel;
import com.yiwu.changething.sec1.model.IdleResModel;
import com.yiwu.changething.sec1.model.IdleShareResModel;
import com.yiwu.changething.sec1.service.IdleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@RestController
@RequestMapping("/idle")
public class IdleController {

    @Autowired
    private IdleService idleService;

    @Autowired
    private CommonService commonService;

    /**
     * 获取商品列表
     *
     * @param idleResModel
     * @return
     */
    @GetMapping("/list")
    public Map<String, Object> getIdleList(@Valid IdleResModel idleResModel) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("idleList", idleService.getIdleList(idleResModel));
        resultMap.put("totalCount", idleService.getIdleCount(idleResModel));
//        resultMap.put("test", commonService.getName());
        return resultMap;
    }


    /**
     * 新增商品信息
     *
     * @param idleModel
     */
    @PostMapping
    public void insert(@RequestBody @Valid IdleModel idleModel) {
        idleService.insert(idleModel);
    }

    /**
     * 修改商品信息
     *
     * @param idleModel
     */
    @PutMapping
    public void update(@RequestBody @Valid IdleModel idleModel) {
        idleService.update(idleModel);
    }

    /**
     * 根據id刪除商品信息
     *
     * @param idleId
     */
    @DeleteMapping("/{idleId}")
    public void deleteById(@PathVariable String idleId) {
        idleService.deleteById(idleId);
    }

    /**
     * 根据id获取商品信息
     *
     * @param idleId
     * @return
     */
    @GetMapping("/{idleId}")
    public IdleBean getIdleById(@PathVariable String idleId) {
        return idleService.getIdleById(idleId);
    }

    /**
     * 更新商品共享状态以及共享值
     *
     * @param idleShareResModel
     */
    @PutMapping("/share")
    public void updateShare(@RequestBody @Valid IdleShareResModel idleShareResModel) {
        idleService.updateShare(idleShareResModel.getShare(), idleShareResModel.getShareValue(),
                idleShareResModel.getIdleId());
    }
}
