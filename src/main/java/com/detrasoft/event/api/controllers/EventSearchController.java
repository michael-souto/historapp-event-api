package com.detrasoft.event.api.controllers;

import com.detrasoft.event.api.assemblers.EventAssembler;
import com.detrasoft.event.domain.dtos.EventDTO;
import com.detrasoft.event.domain.entities.Event;
import com.detrasoft.event.domain.repositories.EventRepository;
import com.detrasoft.event.domain.specifications.EventSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/event/search")
public class EventSearchController {

    @Autowired

    private EventRepository _eventRepository;

    @Autowired
    private EventAssembler _eventAssembler;

    @GetMapping(value = "")
    public ResponseEntity<Page<EventDTO>> search(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "description", required = false) String description,
            @RequestParam(name = "tag", required = false) String tag,
            @RequestParam(name = "all", required = false) String all,
            Pageable pageable) {

        Page<Event> list = (all != null && !all.isBlank())
                ? _eventRepository.findAll(EventSpecs.byAllFields(all), pageable)
                : _eventRepository.findAll(EventSpecs.byTitle(title)
                .and(EventSpecs.byDescription(description))
                .and(EventSpecs.byTag(tag)), pageable);

        Page<EventDTO> resultList = new PageImpl<EventDTO>(
                list.getContent().stream()
                        .map(obj -> _eventAssembler.toModel(obj)).toList(), pageable, list.getTotalElements());

        return ResponseEntity.ok().body(resultList);
    }
}