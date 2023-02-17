package com.detrasoft.event.domain.services;

import br.com.detrasoft.framework.cloudclient.service.GenericClientService;
import com.detrasoft.event.api.dtos.CharacterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "character",  fallback = CharacterClientFallback.class )
public interface CharacterClientService extends GenericClientService<CharacterDTO> {

    @RequestMapping(value = "/character/by-list-id", method = RequestMethod.POST)
    ResponseEntity<List<CharacterDTO>> findByListId(@RequestBody List<Long> body);
}


