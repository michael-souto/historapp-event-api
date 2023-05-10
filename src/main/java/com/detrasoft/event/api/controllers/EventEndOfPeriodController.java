package com.detrasoft.event.api.controllers;

import com.detrasoft.event.api.assemblers.EventAssembler;
import com.detrasoft.event.api.converters.HistoricalDateConverter;
import com.detrasoft.event.domain.dtos.EventDTO;
import com.detrasoft.event.domain.dtos.date.HistoricalDateDTO;
import com.detrasoft.event.domain.services.EventCRUDService;
import com.detrasoft.framework.api.controllers.jackson.ResponseView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/event")
public class EventEndOfPeriodController {
    @Autowired
    HistoricalDateConverter historicalDateConverter;
    @Autowired
    EventCRUDService service;
    @Autowired
    EventAssembler assembler;

    @JsonView(ResponseView.put.class)
    @PutMapping(value = "/{id}/end-of-period")
    public ResponseEntity<EventDTO> setEndOfPeriod(@PathVariable Long id, @RequestBody @Valid HistoricalDateDTO dto) {
        return ResponseEntity.ok().body(assembler.toModel(
                ((EventCRUDService) service).setEndOfPeriod(
                        id, historicalDateConverter.toEntity(dto)),
                true));
    }

    @DeleteMapping(value = "/{id}/end-of-period")
    public ResponseEntity<Void> deleteEndOfPeriod(@PathVariable Long id) {
        ((EventCRUDService) service).setEndOfPeriod(id, null);
        return ResponseEntity.noContent().build();
    }
}
