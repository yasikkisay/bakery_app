package com.bakery.test.entities.filters;

import com.bakery.test.entities.Cake;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.GregorianCalendar;

@Component
public abstract class CakeSpecifications {

    public static Specification<Cake> isFresh() {
        return new Specification<Cake>() {
            @Override
            public Predicate toPredicate(Root<Cake> root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThan(root.get("validUntilDate"), new GregorianCalendar().getTime());
            }
        };
    }
}
