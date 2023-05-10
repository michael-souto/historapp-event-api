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
public class EventStartOfPeriodController {

    @Autowired
    HistoricalDateConverter historicalDateConverter;
    @Autowired
    EventCRUDService service;
    @Autowired
    EventAssembler assembler;

    @JsonView(ResponseView.put.class)
    @PutMapping(value = "/{id}/start-of-period")
    public ResponseEntity<EventDTO> setStartOfPeriod(@PathVariable Long id, @RequestBody @Valid HistoricalDateDTO dto) {
        return ResponseEntity.ok().body(assembler.toModel(
                ((EventCRUDService) service).setStartOfPeriod(
                        id, historicalDateConverter.toEntity(dto)),
                true));
    }

    @DeleteMapping(value = "/{id}/start-of-period")
    public ResponseEntity<Void> deleteStartOfPeriod(@PathVariable Long id) {
        ((EventCRUDService) service).setStartOfPeriod(id, null);
        return ResponseEntity.noContent().build();
    }
}
