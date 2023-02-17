package com.detrasoft.event.api.converters;

import com.detrasoft.event.api.dtos.date.CalendarDateDTO;
import com.detrasoft.event.domain.entities.date.CalendarDate;
import com.detrasoft.framework.api.dto.converters.GenericEntityDTOConverter;
import org.springframework.stereotype.Component;

@Component
public class CalendarDateConverter extends GenericEntityDTOConverter<CalendarDate, CalendarDateDTO> {
}
