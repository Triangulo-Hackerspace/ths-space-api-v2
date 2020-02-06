package br.net.triangulohackerspace.thsspaceapi.controller;

import br.net.triangulohackerspace.thsspaceapi.domain.Spacefed;
import br.net.triangulohackerspace.thsspaceapi.service.SpacefedService;
import br.net.triangulohackerspace.thsspaceapi.service.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/spacefeds")
public class SpacefedController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpacefedController.class);

	private final SpacefedService spacefedService;

	@Autowired
	public SpacefedController(SpacefedService spacefedService) {
		this.spacefedService = spacefedService;
	}

	@PostMapping
	public Spacefed createSpacefed(@RequestBody @Valid final Spacefed spacefed) {
		LOGGER.debug("Received request to create the {}", spacefed);
		return spacefedService.save(spacefed);
	}

	@GetMapping
	public List<Spacefed> listSpacefeds() {
		LOGGER.debug("Received request to list all spacefeds");
		return spacefedService.getList();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleSpacefedAlreadyExistsException(AlreadyExistsException e) {
		return e.getMessage();
	}

}
