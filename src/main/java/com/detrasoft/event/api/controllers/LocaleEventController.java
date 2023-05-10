package com.detrasoft.event.api.controllers;

import com.detrasoft.event.api.assemblers.EventAssembler;
import com.detrasoft.event.domain.dtos.EventDTO;
import com.detrasoft.event.domain.services.EventCRUDService;
import com.detrasoft.framework.api.controllers.jackson.ResponseView;
import com.detrasoft.framework.core.context.GenericContext;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/event")
public class LocaleEventController {
    @Autowired
    EventCRUDService service;
    @Autowired
    EventAssembler assembler;

    @JsonView(ResponseView.put.class)
    @PostMapping(value = "/{id}/locales/{idLocale}")
    public ResponseEntity<EventDTO> editLocale(@PathVariable Long id, @PathVariable Long idLocale) {
        return ResponseEntity.ok().body(assembler.toModel(
                ((EventCRUDService) service).editLocale(
                        id, idLocale, false),
                true));
    }

    @DeleteMapping(value = "/{id}/locales/{idLocale}")
    public ResponseEntity<Void> deleteDeathDate(@PathVariable Long id, @PathVariable Long idLocale) {
        ((EventCRUDService) service).editLocale(id, idLocale,true);
        return ResponseEntity.noContent().build();
    }
}
