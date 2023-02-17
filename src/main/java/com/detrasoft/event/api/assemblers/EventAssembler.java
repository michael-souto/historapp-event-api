package com.detrasoft.event.api.assemblers;

import com.detrasoft.event.api.controllers.EventController;
import com.detrasoft.event.api.converters.HistoricalDateConverter;
import com.detrasoft.event.api.dtos.CharacterDTO;
import com.detrasoft.event.api.dtos.EventDTO;
import com.detrasoft.event.domain.entities.Event;
import com.detrasoft.event.domain.services.CharacterClientService;
import com.detrasoft.framework.api.dto.converters.GenericRepresentationModelDTOAssembler;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log
@Component
public class EventAssembler extends GenericRepresentationModelDTOAssembler<Event, EventDTO> {

    @Autowired
    private HistoricalDateConverter historicalDateConverter;

    @Autowired
    private CharacterClientService characterClientService;

    public EventAssembler() {
        super(EventController.class, EventDTO.class);
    }

    @Override
    protected void copyEntityToDto(Event obj, EventDTO dto) {
        super.copyEntityToDto(obj, dto);
        if (obj.getFirstPeriod() != null) {
            dto.setFirstPeriod(historicalDateConverter.toDto(obj.getFirstPeriod()));
        }
        if (obj.getFinalPeriod() != null) {
            dto.setFinalPeriod(historicalDateConverter.toDto(obj.getFinalPeriod()));
        }
        if (obj.getIdsCharacters() != null && obj.getIdsCharacters().size() > 0) {
            var request = characterClientService.findByListId(obj.getIdsCharacters());
            if (request != null && request.getBody() != null && request.getBody().size() > 0) {
                dto.setCharacters(request.getBody().stream().map(
                        x-> CharacterDTO
                                .builder()
                                    .id(x.getId())
                                    .name(x.getName())
                                .build()
                ).toList());
            }
        }
    }

    @Override
    protected void copyDtoToEntity(EventDTO dto, Event event) {
        super.copyDtoToEntity(dto, event);
        if (dto.getFirstPeriod() != null) {
            event.setFirstPeriod(historicalDateConverter.toEntity(dto.getFirstPeriod()));
        }
        if (dto.getFinalPeriod() != null) {
            event.setFinalPeriod(historicalDateConverter.toEntity(dto.getFinalPeriod()));
        }
        if (dto.getCharacters() != null && dto.getCharacters().size() > 0) {
            event.setIdsCharacters(dto.getCharacters().stream().map(CharacterDTO::getId).toList());
        }
    }

    public void fallbackMethod(Exception ex) {
        try {
            throw ex;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
