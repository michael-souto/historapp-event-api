package com.detrasoft.event.api.dtos;

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
@Relation(collectionRelation = "characters")
public class CharacterDTO extends GenericRepresentationModelDTO<CharacterDTO> {

    @JsonView(ResponseView.findAndPersist.class)
    private Long id;

    @JsonView(ResponseView.findAndPersist.class)
    private String name;
}
