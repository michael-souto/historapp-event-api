package com.detrasoft.event.domain.repositories;

import com.detrasoft.event.domain.entities.Event;
import com.detrasoft.framework.crud.repositories.GenericCRUDRepository;

import java.util.Collection;
import java.util.List;

public interface EventRepository  extends GenericCRUDRepository<Event> {
    List<Event> findByIdIn(List<Long> body);
}
