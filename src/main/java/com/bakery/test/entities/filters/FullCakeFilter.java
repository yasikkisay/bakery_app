package com.bakery.test.entities.filters;


import com.bakery.test.models.enums.StatusType;

import java.util.ArrayList;
import java.util.List;

public class FullCakeFilter extends CakeTitleFilter {

    private String statusType;
    private Integer page = super.getPage();
    private Integer limit = super.getLimit();
    private String title = super.getTitle();

    public FullCakeFilter(
            Integer page,
            Integer limit,
            String title,
            String status)
    {
        super(page, limit, title);
//        this.statusType = StatusType.fromValue(status);
        this.statusType = status;
    }

    public String getStringStatusType() {
        return statusType;
    }

    public void setStringStatusTypes(List<StatusType> statusTypes) {
        this.statusType = statusType;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }
}
