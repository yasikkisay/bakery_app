package com.bakery.test.entities.filters;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CakeTitleFilter extends BaseFilter {

//    @NotNull
    @Size(min = 3, max = 10)
    private String title;
    private Integer page = super.getPage();
    private Integer limit = super.getLimit();

//    public CakeTitleFilter(
//            Integer page,
//            Integer limit)
//    {
//        super(page, limit);
//    }

    public CakeTitleFilter(
            Integer page,
            Integer limit,
            String title)
    {
        super(page, limit);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public Integer getPage() {
        return page;
    }

    @Override
    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public Integer getLimit() {
        return limit;
    }

    @Override
    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
