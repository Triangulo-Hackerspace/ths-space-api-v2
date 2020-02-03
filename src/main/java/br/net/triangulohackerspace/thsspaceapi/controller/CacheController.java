package br.net.triangulohackerspace.thsspaceapi.controller;

import br.net.triangulohackerspace.thsspaceapi.domain.Cache;
import br.net.triangulohackerspace.thsspaceapi.service.CacheService;
import br.net.triangulohackerspace.thsspaceapi.service.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/caches")
public class CacheController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CacheController.class);
	

	//private final SpaceServiceFactory<Cache, Long> spaceServiceFactory;

	private final CacheService cacheService;

	@Autowired
	public CacheController(CacheService cacheService) {
		this.cacheService = cacheService;
	}

	@RequestMapping(value = "/cache", method = RequestMethod.POST)
	public Cache createCache(@RequestBody @Valid final Cache cache) {
		
		LOGGER.debug("Received request to create the {}", cache);
		//return spaceServiceFactory.getService(Services.Cache).save(cache);
		return cacheService.save(cache);
	}

	@RequestMapping(value = "/cache", method = RequestMethod.GET)
	public List<Cache> listCaches() {
		LOGGER.debug("Received request to list all caches");
		//return spaceServiceFactory.getService(Services.Cache).getList();
		return cacheService.getList();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleCacheAlreadyExistsException(AlreadyExistsException e) {
		return e.getMessage();
	}

}
