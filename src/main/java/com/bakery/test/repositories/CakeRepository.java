package com.bakery.test.repositories;

import com.bakery.test.entities.Cake;
import com.bakery.test.entities.filters.BaseFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface CakeRepository extends CrudRepository<Cake, Integer> {

//    CompletableFuture<Cake> getItem(long id);
//    CompletableFuture<List<Cake>> getRangeOfItem(BaseFilter filter);
//    CompletableFuture<Void> addItem(Cake cake);
//    CompletableFuture<Void> updateItem(Cake cake);
//    CompletableFuture<Void> removeItem(Cake cake);
//    CompletableFuture<Long> getTotal();
}
