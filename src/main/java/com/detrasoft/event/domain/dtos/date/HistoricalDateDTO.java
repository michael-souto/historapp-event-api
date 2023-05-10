package com.detrasoft.event.domain.dtos.date;

import com.detrasoft.framework.api.controllers.jackson.ResponseView;
import com.detrasoft.framework.api.dto.GenericRepresentationModelDTO;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "dates")
public class HistoricalDateDTO extends GenericRepresentationModelDTO<HistoricalDateDTO> {

    @JsonView(ResponseView.findAndPersist.class)
    private Long id;

    @JsonView(ResponseView.findAndPersist.class)
    private Instant date;

    @JsonView(ResponseView.findAndPersist.class)
    private List<CalendarDateDTO> datesCalendars;
}
