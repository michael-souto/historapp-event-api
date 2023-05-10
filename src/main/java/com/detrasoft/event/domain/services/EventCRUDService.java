package com.detrasoft.event.domain.services;

import com.detrasoft.event.domain.entities.Event;
import com.detrasoft.event.domain.entities.date.HistoricalDate;
import com.detrasoft.event.domain.repositories.EventRepository;
import com.detrasoft.event.domain.repositories.HistoricalDateRepository;
import com.detrasoft.framework.crud.library.GeneralFunctionsCRUD;
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

    @Autowired
    public HistoricalDateRepository historicalDateRepository;

    public Event editLocale(Long idEvent, Long idLocale, boolean removeLocale) {
        var eventFound = this.findById(idEvent);

        if (removeLocale) {
            eventFound.getIdsLocales().removeIf(x-> x.equals(idLocale));
        } else {
            if (!eventFound.getIdsLocales().contains(idLocale)){
                eventFound.getIdsLocales().add(idLocale);
            }
        }

        eventFound = repository.save(eventFound);
        return eventFound;
    }
    public Event editCharacter(Long idEvent, Long idCharacter, boolean removeCharacter) {
        var eventFound = this.findById(idEvent);

        if (removeCharacter) {
            eventFound.getIdsCharacters().removeIf(x-> x.equals(idCharacter));
        } else {
            if (!eventFound.getIdsCharacters().contains(idCharacter)){
                eventFound.getIdsCharacters().add(idCharacter);
            }
        }

        eventFound = repository.save(eventFound);
        return eventFound;
    }

    public Event setStartOfPeriod(Long idEvent, HistoricalDate historicalDate) {
        var eventFound = this.findById(idEvent);
        var date = eventFound.getStartOfPeriod();
        eventFound.setStartOfPeriod(updateDate(historicalDate, eventFound.getStartOfPeriod()));
        beforeUpdate(eventFound);
        eventFound = repository.save(eventFound);

        if (GeneralFunctionsCRUD.checkEmptyOrNull(historicalDate) && (date != null && date.getId() != null)) {
            historicalDateRepository.delete(date);
        }
        return eventFound;
    }

    public Event setEndOfPeriod(Long idEvent, HistoricalDate historicalDate) {
        var eventFound = this.findById(idEvent);
        var date = eventFound.getEndOfPeriod();
        eventFound.setEndOfPeriod(updateDate(historicalDate, eventFound.getEndOfPeriod()));
        beforeUpdate(eventFound);
        eventFound = repository.save(eventFound);

        if (GeneralFunctionsCRUD.checkEmptyOrNull(historicalDate) && (date != null && date.getId() != null)) {
            historicalDateRepository.delete(date);
        }
        return eventFound;
    }
    
    protected void beforeSave(Event entity) {
        if (entity.getStartOfPeriod() != null
                && entity.getStartOfPeriod().getDatesCalendars() != null
                && entity.getStartOfPeriod().getDatesCalendars().size() > 0) {
            entity.getStartOfPeriod().getDatesCalendars().forEach(x-> x.setHistoricalDate(entity.getStartOfPeriod()));
        }
        if (entity.getEndOfPeriod() != null
                && entity.getEndOfPeriod().getDatesCalendars() != null
                && entity.getEndOfPeriod().getDatesCalendars().size() > 0) {
            entity.getEndOfPeriod().getDatesCalendars().forEach(x-> x.setHistoricalDate(entity.getEndOfPeriod()));
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

    public HistoricalDate updateDate(HistoricalDate newHistoricalDate, HistoricalDate oldHistoricalDate) {
        if (!GeneralFunctionsCRUD.checkEmptyOrNull(newHistoricalDate)) {
            if (oldHistoricalDate != null && oldHistoricalDate.getId() != null) {
                oldHistoricalDate.setDate(newHistoricalDate.getDate());
                oldHistoricalDate.getDatesCalendars().clear();
                if (newHistoricalDate.getDatesCalendars() != null
                        && newHistoricalDate.getDatesCalendars().size() > 0) {
                    oldHistoricalDate.getDatesCalendars().addAll(newHistoricalDate.getDatesCalendars());
                }
                return oldHistoricalDate;
            } else {
                return (newHistoricalDate);
            }
        } else {
            return (null);
        }
    }


}
