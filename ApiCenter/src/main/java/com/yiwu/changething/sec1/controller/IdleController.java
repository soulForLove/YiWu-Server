package com.yiwu.changething.sec1.controller;

import com.yiwu.changething.sec1.model.IdleResModel;
import com.yiwu.changething.sec1.service.IdleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 获取商品列表
     *
     * @param idleResModel
     * @return
     */
    @GetMapping("/list")
    public Map<String, Object> getIdleList(@Valid IdleResModel idleResModel) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("idleList", idleService.getIdleList(idleResModel.getName(), idleResModel.getIdleOrder(),
                idleResModel.getOrderType(), idleResModel.getPage(), idleResModel.getPageSize()));
        resultMap.put("totalCount", idleService.getIdleCount(idleResModel.getName()));
        return resultMap;
    }
}
