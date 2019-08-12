package com.bakery.test.services;

import com.bakery.test.entities.Cake;
import com.bakery.test.entities.filters.BaseFilter;
import com.bakery.test.models.dtos.CakeDto;
import com.bakery.test.models.views.CakeView;

import java.util.concurrent.CompletableFuture;

public interface CakeService {

    CompletableFuture<CakeDto> getItem(long id);
    CompletableFuture<CakeView> getView(BaseFilter filter);
    CompletableFuture<Void> saveItem(CakeDto cake);
    CompletableFuture<Void> removeItem(long id);
    CompletableFuture<Long> getTotal();
}
