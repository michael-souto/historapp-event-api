package com.detrasoft.event.api.converters;

import com.detrasoft.event.domain.dtos.date.HistoricalDateDTO;
import com.detrasoft.event.domain.entities.date.HistoricalDate;
import com.detrasoft.framework.api.dto.converters.GenericEntityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class HistoricalDateConverter extends GenericEntityDTOConverter<HistoricalDate, HistoricalDateDTO> {

    @Autowired
    private CalendarDateConverter calendarDateConverter;
    @Override
    protected void copyDtoToEntity(HistoricalDateDTO dto, HistoricalDate entity) {
        super.copyDtoToEntity(dto, entity);
        if (dto.getDatesCalendars() != null && dto.getDatesCalendars().size() > 0) {
            entity.setDatesCalendars(new ArrayList<>());
            dto.getDatesCalendars().forEach(x -> entity.getDatesCalendars().add(calendarDateConverter.toEntity(x)));
        }
    }

    @Override
    protected void copyEntityToDto(Object obj, HistoricalDateDTO dto) {
        super.copyEntityToDto(obj, dto);
        if (((HistoricalDate)obj).getDatesCalendars() != null && ((HistoricalDate)obj).getDatesCalendars().size() > 0) {
            dto.setDatesCalendars(new ArrayList<>());
            ((HistoricalDate)obj).getDatesCalendars().forEach(x -> dto.getDatesCalendars().add(calendarDateConverter.toDto(x)));
        }
    }
}
