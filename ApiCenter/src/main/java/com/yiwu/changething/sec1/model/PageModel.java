package com.yiwu.changething.sec1.model;

import javax.validation.constraints.Min;

/**
 * 这个model用于分页，page从1开始，表示第几页的意思
 * Created by LinZhongtai <linzhongtai@gengee.cn>
 */
public class PageModel {

    @Min(1)
    private Integer page;

    private Integer pageSize;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
