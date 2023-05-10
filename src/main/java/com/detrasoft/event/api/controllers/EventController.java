package com.detrasoft.event.api.controllers;

import com.detrasoft.event.api.assemblers.EventAssembler;
import com.detrasoft.event.domain.dtos.EventDTO;
import com.detrasoft.event.domain.services.EventCRUDService;
import com.detrasoft.framework.api.controllers.hateoas.GenericHateoasCRUDController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/event")
public class EventController extends GenericHateoasCRUDController<EventDTO> {

    @Autowired
    public EventController(EventCRUDService service, EventAssembler assembler) {
        super(service, assembler);
    }
}
