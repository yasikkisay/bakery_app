package com.bakery.test.controllers;

import com.bakery.test.entities.Cake;
import com.bakery.test.entities.filters.BaseFilter;
import com.bakery.test.entities.filters.CakeTitleFilter;
import com.bakery.test.entities.filters.FullCakeFilter;
import com.bakery.test.models.dtos.CakeDto;
import com.bakery.test.models.enums.StatusType;
import com.bakery.test.models.inputs.NewCakeInputParams;
import com.bakery.test.models.views.CakeView;
import com.bakery.test.repositories.CakeRepository;
import com.bakery.test.services.CakeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CakeController {

    @Autowired
    CakeServiceImpl service;

    @Autowired
    CakeRepository cakeRepository;

//    @GetMapping("test")
//    public Iterable<Cake> getAll() {
//        return service.getAllCakes();
//    }
//
//    @GetMapping("/test/{id}")
//    public CakeDto cake (@PathVariable("id") int id) {
//        return service.getCakeById(id);
//    }

    @GetMapping("/test")
    public List<CakeDto> getAllCakes() {
        return service.getAllCakes();
    }

    @GetMapping("/test/{id}")
    public CakeDto cake (@PathVariable("id") int id) {
        return service.getCake(id);
    }

    @GetMapping("/items")
    public CakeView cakeView() {
        return service.getCakeView();
    }

    @GetMapping("/count")
    public Long countAllItems() {
        return service.countAll();
    }

    @PostMapping("/save")
    public void saveNewCake(
            @RequestBody
            NewCakeInputParams newCakeInputParams
    ) {
        service.saveCake(newCakeInputParams);
    }

    @GetMapping("/find")
    public List<Cake> find(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit,
            @Valid @ModelAttribute() BaseFilter filter
            ) {
        return service.findCakes(page, limit);
    }

    @GetMapping("/find2")
    public CakeView findByTitle(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "title") String title,
            @Valid @ModelAttribute() CakeTitleFilter filter
    ) {
        return service.findByTitle(new CakeTitleFilter(page, limit, title));
    }

    @GetMapping("/find3")
    public CakeView fullsearch(
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "limit", required = false) Integer limit,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "status", required = false) String status,
            @Valid @ModelAttribute() FullCakeFilter filter
            ) {
//        System.out.printf(String.valueOf(statusTypes.size()));
//        if (statusTypes.contains(StatusType.FRESH)) {
//            System.out.println("it is fresh");
//        } if (statusTypes.contains(StatusType.STALE)) {
//            System.out.println("it is stale");
//        } else {
//            System.out.println("don't know");
//        }
        return service.fullCakeSearch(new FullCakeFilter(page, limit, title, status));
    }
}
