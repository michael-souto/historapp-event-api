package com.detrasoft.event.domain.dtos;

import com.detrasoft.event.domain.dtos.date.HistoricalDateDTO;
import com.detrasoft.framework.api.controllers.jackson.ResponseView;
import com.detrasoft.framework.api.dto.GenericRepresentationModelDTO;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "events")
public class EventDTO extends GenericRepresentationModelDTO<EventDTO> {

    @JsonView({ResponseView.findAndPersist.class })
    private Long id;

    @NotBlank
    @JsonView({ResponseView.findAndPersist.class })
    private String title;

    @NotBlank
    @JsonView({ResponseView.findAndPersist.class })
    private String description;

    @JsonView({ResponseView.findAndPersist.class })
    private String tags;

    @JsonView({ResponseView.findAndPersist.class })
    private HistoricalDateDTO startOfPeriod;

    @JsonView({ResponseView.findAndPersist.class })
    private HistoricalDateDTO endOfPeriod;

    @JsonView({ ResponseView.persist.class, ResponseView.findById.class})
    private List<CharacterDTO> characters;

    @JsonView({ ResponseView.persist.class, ResponseView.findById.class})
    private List<LocaleDTO> locales;

    @JsonView({ ResponseView.post.class, ResponseView.find.class})
    private Instant createAt;

    @JsonView({ ResponseView.put.class, ResponseView.find.class})
    private Instant updateAt;
}
