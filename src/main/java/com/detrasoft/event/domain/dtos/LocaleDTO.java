package com.detrasoft.event.domain.dtos;

import com.detrasoft.framework.api.controllers.jackson.ResponseView;
import com.detrasoft.framework.api.dto.GenericRepresentationModelDTO;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "locales")
public class LocaleDTO extends GenericRepresentationModelDTO<LocaleDTO> {

    @JsonView(ResponseView.findAndPersist.class)
    private Long id;

    @JsonView(ResponseView.findAndPersist.class)
    private String name;
}
