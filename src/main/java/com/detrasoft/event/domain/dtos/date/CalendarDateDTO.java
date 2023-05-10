package com.detrasoft.event.domain.dtos.date;

import com.detrasoft.framework.api.controllers.jackson.ResponseView;
import com.detrasoft.framework.api.dto.GenericRepresentationModelDTO;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "calendar-date")
public class CalendarDateDTO extends GenericRepresentationModelDTO<CalendarDateDTO> {
    private Long id;

    @JsonView(ResponseView.findAndPersist.class)
    private int historicDay;

    @JsonView(ResponseView.findAndPersist.class)
    private int historicMonth;

    @JsonView(ResponseView.findAndPersist.class)
    private int historicYear;

    @JsonView(ResponseView.findAndPersist.class)
    private boolean negativeYear;

    @JsonView(ResponseView.findAndPersist.class)
    private Long idHistoricMonth;

    @JsonView(ResponseView.findAndPersist.class)
    private String nameHistoricMonth;

    @JsonView(ResponseView.findAndPersist.class)
    private Long idCalendar;

    @JsonView(ResponseView.findAndPersist.class)
    private String nameCalendar;
}
