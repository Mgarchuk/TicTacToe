package com.company.battle.utils.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

public class SpecificationUtils {

    public static <T> void putIfNotNull(List<Specification<T>> specifications,
                                        Object filterValue,
                                        Specification<T> specification) {
        if (filterValue != null) {
            if (!(filterValue instanceof Collection)) {
                specifications.add(specification);
            } else {
                if (!CollectionUtils.isEmpty((Collection<?>) filterValue)) {
                    specifications.add(specification);
                }
            }
        }
    }

    public static <T> Specification<T> createFilterAnd(List<Specification<T>> specifications) {

        return specifications.stream()
                .reduce(Specification::and)
                .orElse(null);
    }
}