package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.sec1.bean.IdleBean;
import com.yiwu.changething.sec1.enums.ShareStatus;
import com.yiwu.changething.sec1.model.IdleModel;
import com.yiwu.changething.sec1.model.IdleResModel;
import com.yiwu.changething.sec1.model.IdleShareResModel;
import com.yiwu.changething.sec1.service.IdleService;
import com.yiwu.changething.sec1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private OrderService orderService;

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
    public void insert(@RequestBody @Valid IdleModel idleModel, HttpServletRequest request) {
        idleService.insert(idleModel, request);
    }

    /**
     * 修改商品信息
     *
     * @param idleModel
     */
    @PutMapping
    public void update(@RequestBody @Valid IdleModel idleModel, HttpServletRequest request) {
        idleService.update(idleModel, request);
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
     * 更新商品共享状态以及共享值、共享周期
     *
     * @param idleShareResModel
     */
    @PutMapping("/shareStatus")
    public void updateShareStatus(@RequestBody @Valid IdleShareResModel idleShareResModel, HttpServletRequest request) {
        idleService.updateShareStatus(idleShareResModel.getShareStatus(), idleShareResModel.getShareValue(),
                idleShareResModel.getIdleId(), idleShareResModel.getShareCycle(), request);
        if (idleShareResModel.getShareStatus().equals(ShareStatus.NOTLOCK)) {
            orderService.updateShareNum(idleShareResModel.getIdleId());
        }
    }
}
