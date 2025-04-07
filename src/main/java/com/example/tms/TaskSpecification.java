package com.example.tms;

import com.example.tms.entity.TaskEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TaskSpecification {
    public static Specification<TaskEntity> getFilteredTasks(Map<String, String> filters){
        return new Specification<TaskEntity>() { // anonymous class
            @Override
            public Predicate toPredicate(Root<TaskEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();

                if(filters.containsKey("status"))
                {
                    predicateList.add(criteriaBuilder.equal(root.get("status"), filters.get("status")));
                }
                if(filters.containsKey("isDeleted"))
                {
                    boolean isDeleted = Boolean.parseBoolean(filters.get("isDeleted"));
                    predicateList.add(criteriaBuilder.equal(root.get("isDeleted"),isDeleted));
                }

                return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));

            }
        };
    }
}




