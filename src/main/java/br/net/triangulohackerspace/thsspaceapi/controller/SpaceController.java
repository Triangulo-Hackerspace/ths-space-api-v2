package br.net.triangulohackerspace.thsspaceapi.controller;

import br.net.triangulohackerspace.thsspaceapi.domain.Space;
import br.net.triangulohackerspace.thsspaceapi.domain.to.SpaceApiTO;
import br.net.triangulohackerspace.thsspaceapi.service.SpaceService;
import br.net.triangulohackerspace.thsspaceapi.service.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/spaces")
public class SpaceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpaceController.class);

	private SpaceService spaceService;

	@Autowired
    public SpaceController(SpaceService spaceService) {
        this.spaceService = spaceService;
    }

    @PostMapping
    public Space createSpace(@RequestBody @Valid final Space space) {
        LOGGER.debug("Received request to create the {}", space);
        return spaceService.save(space);
    }

    @GetMapping
    public List<Space> listSpaces() {
		LOGGER.debug("Received request to list all spaces");
        return spaceService.getList();
    }

    @GetMapping(value = "/api/{spaceId}")
    public SpaceApiTO spaces(@PathVariable("spaceId") Long spaceId) {
		LOGGER.debug("Received request to list all spaces");
        return spaceService.findSpace(spaceId);
    }
    
    //@ExceptionHandler
    //@ResponseStatus(HttpStatus.CONFLICT)
    //public String handleSpaceAlreadyExistsException(AlreadyExistsException e) {
     //   return e.getMessage();
    //}

}
