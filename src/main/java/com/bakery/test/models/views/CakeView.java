package com.bakery.test.models.views;

import com.bakery.test.models.dtos.CakeDto;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

@JsonView
public class CakeView {

    private List<CakeDto> items;
    private Integer total;

    public CakeView() {
    }

    public CakeView(List<CakeDto> items, Integer total) {
        this.items = items;
        this.total = total;
    }

    public List<CakeDto> getItems() {
        return items;
    }

    public void setItems(List<CakeDto> items) {
        this.items = items;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
