package com.detrasoft.event.domain.services.locale;

import br.com.detrasoft.framework.cloudclient.service.GenericClientService;
import com.detrasoft.event.domain.dtos.LocaleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "locale",  fallback = LocaleClientFallback.class )
public interface LocaleClientService  extends GenericClientService<LocaleDTO> {

    @RequestMapping(value = "/locale/by-list-id", method = RequestMethod.POST)
    ResponseEntity<List<LocaleDTO>> findByListId(@RequestBody List<Long> body);
}
