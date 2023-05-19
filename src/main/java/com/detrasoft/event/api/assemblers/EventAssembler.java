package com.detrasoft.event.api.assemblers;

import com.detrasoft.event.api.controllers.EventController;
import com.detrasoft.event.api.converters.HistoricalDateConverter;
import com.detrasoft.event.domain.dtos.CharacterDTO;
import com.detrasoft.event.domain.dtos.EventDTO;
import com.detrasoft.event.domain.dtos.LocaleDTO;
import com.detrasoft.event.domain.entities.Event;
import com.detrasoft.event.domain.services.character.CharacterClientService;
import com.detrasoft.event.domain.services.locale.LocaleClientService;
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

    @Autowired
    private LocaleClientService localeClientService;

    public EventAssembler() {
        super(EventController.class, EventDTO.class);
    }

    @Override
    protected void copyEntityToDto(Event obj, EventDTO dto) {
        super.copyEntityToDto(obj, dto);
        if (obj.getStartOfPeriod() != null) {
            dto.setStartOfPeriod(historicalDateConverter.toDto(obj.getStartOfPeriod()));
        }
        if (obj.getEndOfPeriod() != null) {
            dto.setEndOfPeriod(historicalDateConverter.toDto(obj.getEndOfPeriod()));
        }

        // Characters from API
        if (obj.getIdsCharacters() != null && obj.getIdsCharacters().size() > 0) {
            var request = characterClientService.findByListId(obj.getIdsCharacters());
            if (request != null && request.getBody() != null && request.getBody().size() > 0) {
                dto.setCharacters(request.getBody().stream().map(
                        x -> CharacterDTO
                                .builder()
                                .id(x.getId())
                                .name(x.getName())
                                .build()
                ).toList());
            } else {
                dto.setCharacters(obj.getIdsCharacters().stream().map(
                        x -> CharacterDTO
                                .builder()
                                .id(x)
                                .build()
                ).toList());
            }
        }

        // Locales from API
        if (obj.getIdsLocales() != null && obj.getIdsLocales().size() > 0) {
            var request = localeClientService.findByListId(obj.getIdsLocales());
            if (request != null && request.getBody() != null && request.getBody().size() > 0) {
                dto.setLocales(request.getBody().stream().map(
                        x -> LocaleDTO
                                .builder()
                                .id(x.getId())
                                .name(x.getName())
                                .build()
                ).toList());
            } else {
                dto.setLocales(obj.getIdsLocales().stream().map(
                        x -> LocaleDTO
                                .builder()
                                .id(x)
                                .build()
                ).toList());
            }
        }
    }

    @Override
    protected void copyDtoToEntity(EventDTO dto, Event event) {
        super.copyDtoToEntity(dto, event);
        if (dto.getStartOfPeriod() != null) {
            event.setStartOfPeriod(historicalDateConverter.toEntity(dto.getStartOfPeriod()));
        }
        if (dto.getEndOfPeriod() != null) {
            event.setEndOfPeriod(historicalDateConverter.toEntity(dto.getEndOfPeriod()));
        }
        if (dto.getCharacters() != null && dto.getCharacters().size() > 0) {
            event.setIdsCharacters(dto.getCharacters().stream().map(CharacterDTO::getId).toList());
        }

        if (dto.getLocales() != null && dto.getLocales().size() > 0) {
            event.setIdsLocales(dto.getLocales().stream().map(LocaleDTO::getId).toList());
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
