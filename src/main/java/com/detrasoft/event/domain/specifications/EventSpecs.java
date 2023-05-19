package com.detrasoft.event.domain.specifications;

import com.detrasoft.event.domain.entities.Event;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class EventSpecs {

    public static Specification<Event> byTitle(String title) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();
            if (title != null && !title.isBlank()) {
                predicates.add(
                        builder.like(
                                builder.lower(root.get("title").as(String.class)),
                                "%" + title.toLowerCase() + "%"));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Event> byDescription(String description) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();
            if (description != null && !description.isBlank()) {
                predicates.add(
                        builder.like(
                                builder.lower(root.get("description").as(String.class)),
                                "%" + description.toLowerCase() + "%"));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Event> byTag(String tag) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();
            if (tag != null && !tag.isBlank()) {
                predicates.add(
                        builder.like(
                                builder.lower(root.get("tags").as(String.class)),
                                "%" + tag.toLowerCase() + "%"));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<Event> byAllFields(String word) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();
            if (word != null && !word.isBlank()) {
                predicates.add(
                        builder.like(
                                builder.lower(root.get("tags").as(String.class)),
                                "%" + word.toLowerCase() + "%"));
                predicates.add(
                        builder.like(
                                builder.lower(root.get("description").as(String.class)),
                                "%" + word.toLowerCase() + "%"));
                predicates.add(
                        builder.like(
                                builder.lower(root.get("title").as(String.class)),
                                "%" + word.toLowerCase() + "%"));
            }
            return builder.or(predicates.toArray(new Predicate[0]));
        };
    }
}
