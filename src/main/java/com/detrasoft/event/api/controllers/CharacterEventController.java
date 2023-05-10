package com.detrasoft.event.api.controllers;

import com.detrasoft.event.api.assemblers.EventAssembler;
import com.detrasoft.event.domain.dtos.EventDTO;
import com.detrasoft.event.domain.services.EventCRUDService;
import com.detrasoft.framework.api.controllers.jackson.ResponseView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/event")
public class CharacterEventController {

    @Autowired
    EventCRUDService service;
    @Autowired
    EventAssembler assembler;

    @JsonView(ResponseView.put.class)
    @PostMapping(value = "/{id}/characters/{idCharacter}")
    public ResponseEntity<EventDTO> addCharacter(@PathVariable Long id,@PathVariable Long idCharacter) {
        return ResponseEntity.ok().body(assembler.toModel(
                ((EventCRUDService) service).editCharacter(
                        id, idCharacter, false),
                true));
    }

    @DeleteMapping(value = "/{id}/characters/{idCharacter}")
    public ResponseEntity<Void> deleteDeathDate(@PathVariable Long id, @PathVariable Long idCharacter) {
        ((EventCRUDService) service).editCharacter(id, idCharacter,true);
        return ResponseEntity.noContent().build();
    }
}
