package com.bakery.test.services;

import com.bakery.test.entities.Cake;
import com.bakery.test.entities.filters.BaseFilter;
import com.bakery.test.entities.filters.CakeTitleFilter;
import com.bakery.test.entities.filters.FullCakeFilter;
import com.bakery.test.models.dtos.CakeDto;
import com.bakery.test.models.inputs.NewCakeInputParams;
import com.bakery.test.models.views.CakeView;
import com.bakery.test.repositories.CakeRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class CakeServiceImpl implements CakeService {

    @Autowired
    CakeRepositoryImpl cakeRepository;

    @Autowired
    CacheManager cacheManager;

//    public CakeDto getCakeById(int id) {
//        Optional<CakeDto> optionalCake = Optional.ofNullable(repository.findById((int) id).get().toDto());
//        return optionalCake.orElseThrow(() -> new EntityNotFoundException("Cake with id " + id + " was not found."));
//    }

    public List<CakeDto> getAllCakes() {
        return cakeRepository.getAllItems();
    }

    public CakeDto getCake(int id) {
        return cakeRepository.getItemById(id);
    }

    public CakeView getCakeView() {
        List<CakeDto> list = cakeRepository.getAllItems();
        return new CakeView(list, list.size());
    }

    public Long countAll() {
        return cakeRepository.countItems();
    }

    public void saveCake(NewCakeInputParams newCake) {
        CakeDto cakeDto = new CakeDto(newCake.getCakeTitleInput());
        cakeRepository.addItem(cakeDto, newCake.getFreshDuration());
        cacheManager.getCache("cakes").clear();
    }

    public List<Cake> findCakes(int page, int limit) {
        return cakeRepository.pagingTest(page, limit);
    }

//    public List<Cake> findByTitle(int page, int limit, String title) {
//        return cakeRepository.findByTitle(page, limit, title);
//    }

    public CakeView findByTitle(CakeTitleFilter filter) {
        return new CakeView(cakeRepository.findByTitle(filter), cakeRepository.countItems(filter));
    }

    public CakeView fullCakeSearch(FullCakeFilter filter) {
        return new CakeView(cakeRepository.fullCakeSearch(filter), 999);
    }

    @Override
    public CompletableFuture<CakeDto> getItem(long id) {
        return null;
    }

    @Override
    public CompletableFuture<CakeView> getView(BaseFilter filter) {
        return null;
    }

    @Override
    public CompletableFuture<Void> saveItem(CakeDto cake) {
        return null;
    }

    @Override
    public CompletableFuture<Void> removeItem(long id) {
        return null;
    }

    @Override
    public CompletableFuture<Long> getTotal() {
        return null;
    }


}
