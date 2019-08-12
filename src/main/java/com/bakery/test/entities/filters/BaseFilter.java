package com.bakery.test.entities.filters;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class BaseFilter {

    @Min(1)
    private Integer page;

    @Min(1)
    @Max(value = 3, message = "'limit' parameter couldn't be greater than 3.")
    private Integer limit;

    public BaseFilter(Integer page, Integer limit) {
        this.page = (page == null) ? 1 : page;
        this.limit = (limit == null) ? 3 : limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
