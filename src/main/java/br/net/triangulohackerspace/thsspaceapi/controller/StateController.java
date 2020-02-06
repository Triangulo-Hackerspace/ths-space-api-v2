package br.net.triangulohackerspace.thsspaceapi.controller;

import br.net.triangulohackerspace.thsspaceapi.domain.State;
import br.net.triangulohackerspace.thsspaceapi.domain.to.StateTO;
import br.net.triangulohackerspace.thsspaceapi.service.StateService;
import br.net.triangulohackerspace.thsspaceapi.service.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/states")
public class StateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StateController.class);

    private final StateService stateService;

    @Autowired
    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @PostMapping(path = "/{userId}")
    public State createStateByUser(@RequestBody @Valid final State state, @PathVariable("userId") Long userId, @RequestParam("entry") String entry) {
        LOGGER.debug("Received request to create the {}", state);
        return stateService.saveByUser(state, userId, entry);
    }

    @PostMapping
    public State createState(@RequestBody @Valid final State state) {
        LOGGER.debug("Received request to create the {}", state);
        return stateService.save(state);
    }


    @GetMapping
    public List<StateTO> listStatesBySpace() {
		LOGGER.debug("Received request to list all states");
        return stateService.getStateList();
    }
    
    @RequestMapping(value = "/state", method = RequestMethod.GET)
    public List<State> listStates() {
		LOGGER.debug("Received request to list all states");
        return stateService.getList();
    }

    @RequestMapping(value = "/state/now", method = RequestMethod.GET)
    public StateTO getStateNearNow() {
		LOGGER.debug("Received request to list all states");
        return stateService.findState();
    }

    
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleStateAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}
