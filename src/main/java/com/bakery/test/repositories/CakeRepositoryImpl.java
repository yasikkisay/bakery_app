package com.bakery.test.repositories;

import com.bakery.test.entities.Cake;
import com.bakery.test.entities.filters.CakeTitleFilter;
import com.bakery.test.entities.filters.FullCakeFilter;
import com.bakery.test.models.dtos.CakeDto;
import com.bakery.test.models.enums.StatusType;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

//@Repository
public class CakeRepositoryImpl {

    @Autowired
    CakeRepository repository;
    @Autowired
    ModelMapper modelMapper;

    @PersistenceContext
    EntityManager em;

    @Cacheable(value = "cakes")
    public List<CakeDto> getAllItems() {
        Iterable<Cake> allCakes = repository.findAll();

        Type listType = new TypeToken<List<CakeDto>>() {}.getType();
        List<CakeDto> allCakesDto = modelMapper.map(allCakes, listType);
        return allCakesDto;
    }

    public CakeDto getItemById(int id) {
        Optional<Cake> cake = repository.findById(id);

        Type cakeType = new TypeToken<CakeDto>() {}.getRawType();
        CakeDto cakeDto = modelMapper.map(cake.get(), cakeType);
        return cakeDto;
    }

    public Long countItems() {
        return (Long) em.createNativeQuery("SELECT count(*) FROM cake").getSingleResult();
    }

    public void addItem(CakeDto cakeDto, int daysDuration) {
        repository.save(cakeDto.toEntity(daysDuration));
    }

    public List<Cake> pagingTest(int page, int limit) {
        int firstIndex;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Cake> criteriaQuery = criteriaBuilder.createQuery(Cake.class);
        Root<Cake> cakeRoot = criteriaQuery.from(Cake.class);
        CriteriaQuery<Cake> select = criteriaQuery.select(cakeRoot);
        if (page == 1) {
            firstIndex = 0;
        } else {
            firstIndex = page;
        }

        TypedQuery typedQuery = em.createQuery(select);
        typedQuery.setFirstResult(firstIndex);
        typedQuery.setMaxResults(limit);
        List<Cake> list = typedQuery.getResultList();
        return list;
    }

    public List<CakeDto> findByTitle(CakeTitleFilter filter) {
        int firstIndex;
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Cake> criteriaQuery = criteriaBuilder.createQuery(Cake.class);
        Root<Cake> cakeRoot = criteriaQuery.from(Cake.class);
        CriteriaQuery<Cake> find = criteriaQuery
                .select(cakeRoot)
                .where(criteriaBuilder
                        .like(cakeRoot.get("cakeTitle"), "%"+filter.getTitle()+"%"));

//        if (filter.getPage() == 1) {
//            firstIndex = 0;
//        } else {
//            firstIndex = filter.getPage();
//        }

        TypedQuery typedQuery = em.createQuery(find);
        typedQuery.setFirstResult(filter.getLimit() * (filter.getPage() - 1));
        typedQuery.setMaxResults(filter.getLimit());
        List<Cake> list = typedQuery.getResultList();

        Type listType = new TypeToken<List<CakeDto>>() {}.getType();
        List<CakeDto> foundCakes = modelMapper.map(list, listType);

        return foundCakes;
    }

    public Integer countItems(CakeTitleFilter filter) {
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
//        Root<Cake> cakeRoot = criteriaQuery.from(Cake.class);
//
//        criteriaQuery.multiselect(criteriaBuilder.count(cakeRoot.get("id")));
//        criteriaQuery.where(criteriaBuilder.like(cakeRoot.get("cakeTitle"), "%"+filter.getTitle()+"%"));
//
//        List<Long> list = em.createQuery(criteriaQuery).getResultList();
//        for (Long object : list) {
//            System.out.println(object);
//        }
//        return list.get(0).intValue();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Cake> cakeRoot = criteriaQuery.from(Cake.class);

        criteriaQuery.select(criteriaBuilder.count(cakeRoot.get("id")));
        criteriaQuery.where(criteriaBuilder.like(cakeRoot.get("cakeTitle"), "%"+filter.getTitle()+"%"));

        Long count = em.createQuery(criteriaQuery).getSingleResult();
        return count.intValue();
    }

    public List<CakeDto> fullCakeSearch(FullCakeFilter filter) {
        int firstIndex;
        List<Predicate> predicates = new ArrayList<Predicate>();
        Predicate titlePredicate;
        Predicate freshPredicate;

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Cake> criteriaQuery = criteriaBuilder.createQuery(Cake.class);
        Root<Cake> cakeRoot = criteriaQuery.from(Cake.class);

        if (filter.getTitle() != null) {
            titlePredicate = criteriaBuilder.like(cakeRoot.get("cakeTitle"), "%"+filter.getTitle()+"%");
            predicates.add(titlePredicate);
        }
        if (filter.getStringStatusType() != null) {
            if (StatusType.fromValue(filter.getStringStatusType()).equals(StatusType.FRESH)) {
                freshPredicate = criteriaBuilder.greaterThanOrEqualTo(cakeRoot.get("validUntilDate"), criteriaBuilder.currentDate());
            } else {
                freshPredicate = criteriaBuilder.lessThan(cakeRoot.get("validUntilDate"), criteriaBuilder.currentDate());
            }
            predicates.add(freshPredicate);
        }

//        titlePredicate = criteriaBuilder.like(cakeRoot.get("cakeTitle"), "%"+filter.getTitle()+"%");
//        if (StatusType.fromValue(filter.getStringStatusType()).equals(StatusType.FRESH)) {
//            freshPredicate = criteriaBuilder.greaterThanOrEqualTo(cakeRoot.get("validUntilDate"), criteriaBuilder.currentDate());
//        } else {
//            freshPredicate = criteriaBuilder.lessThan(cakeRoot.get("validUntilDate"), criteriaBuilder.currentDate());
//        }

        criteriaQuery.select(cakeRoot);
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));


        TypedQuery typedQuery = em.createQuery(criteriaQuery);
        typedQuery.setFirstResult(filter.getLimit() * (filter.getPage() - 1));
        typedQuery.setMaxResults(filter.getLimit());
        List<Cake> list = typedQuery.getResultList();

        Type listType = new TypeToken<List<CakeDto>>() {}.getType();
        List<CakeDto> foundCakes = modelMapper.map(list, listType);

        return foundCakes;
    }
}
