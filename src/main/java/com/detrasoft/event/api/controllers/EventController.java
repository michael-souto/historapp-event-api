package com.detrasoft.event.api.controllers;

import com.detrasoft.event.api.assemblers.EventAssembler;
import com.detrasoft.event.domain.dtos.EventDTO;
import com.detrasoft.event.domain.repositories.EventRepository;
import com.detrasoft.event.domain.services.EventCRUDService;
import com.detrasoft.framework.api.controllers.hateoas.GenericHateoasCRUDController;
import com.detrasoft.framework.api.controllers.jackson.ResponseView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/event")
public class EventController extends GenericHateoasCRUDController<EventDTO> {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    public EventController(EventCRUDService service, EventAssembler assembler) {
        super(service, assembler);
    }

    @JsonView(ResponseView.findAll.class)
    @PostMapping(value = "/by-list-id")
    public ResponseEntity<List<EventDTO>> findByListId(@RequestBody List<Long> body) {
        return ResponseEntity.ok(
                eventRepository.findByIdIn(body)
                        .stream()
                        .map(obj -> assembler.toModel(obj, true)).toList()
        );
    }
}
