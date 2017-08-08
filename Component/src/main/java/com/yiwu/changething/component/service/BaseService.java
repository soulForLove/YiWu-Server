package com.yiwu.changething.component.service;

import com.yiwu.changething.common.service.CommonService;
import org.springframework.stereotype.Service;

/**
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
@Service
public class BaseService implements CommonService {

    public String getName() {
        return "success";
    }
}
