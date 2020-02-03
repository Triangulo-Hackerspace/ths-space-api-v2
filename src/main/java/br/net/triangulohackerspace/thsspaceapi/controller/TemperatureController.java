package br.net.triangulohackerspace.thsspaceapi.controller;

import br.net.triangulohackerspace.thsspaceapi.domain.Temperature;
import br.net.triangulohackerspace.thsspaceapi.service.TemperatureService;
import br.net.triangulohackerspace.thsspaceapi.service.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/temperatures")
public class TemperatureController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureController.class);
    
    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @RequestMapping(value = "/temperature", method = RequestMethod.POST)
    public Temperature createTemperature(@RequestBody @Valid final Temperature temperature) {
        LOGGER.debug("Received request to create the {}", temperature);
        return temperatureService.save(temperature);
    }

    @RequestMapping(value = "/temperature", method = RequestMethod.GET)
    public List<Temperature> listTemperatures() {
        LOGGER.debug("Received request to list all temperatures");
        return temperatureService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleTemperatureAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}
