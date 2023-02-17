package com.detrasoft.event.domain.services;

import com.detrasoft.event.domain.entities.Event;
import com.detrasoft.event.domain.repositories.EventRepository;
import com.detrasoft.framework.crud.services.crud.GenericCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EventCRUDService extends GenericCRUDService<Event> {

    @Autowired
    public EventCRUDService(EventRepository repository) {
        super(repository);
    }

    protected void beforeSave(Event entity) {
        if (entity.getFirstPeriod() != null
                && entity.getFirstPeriod().getDatesCalendars() != null
                && entity.getFirstPeriod().getDatesCalendars().size() > 0) {
            entity.getFirstPeriod().getDatesCalendars().forEach(x-> x.setHistoricalDate(entity.getFirstPeriod()));
        }
        if (entity.getFinalPeriod() != null
                && entity.getFinalPeriod().getDatesCalendars() != null
                && entity.getFinalPeriod().getDatesCalendars().size() > 0) {
            entity.getFinalPeriod().getDatesCalendars().forEach(x-> x.setHistoricalDate(entity.getFinalPeriod()));
        }
    }
    @Override
    protected void beforeInsert(Event entity) {
        beforeSave(entity);
        entity.setCreateAt(Instant.now());
        super.beforeInsert(entity);
    }

    @Override
    public void beforeUpdate(Event entity) {
        beforeSave(entity);
        entity.setUpdateAt(Instant.now());
        super.beforeUpdate(entity);
    }
}
