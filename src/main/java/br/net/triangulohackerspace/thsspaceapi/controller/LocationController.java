package br.net.triangulohackerspace.thsspaceapi.controller;

import br.net.triangulohackerspace.thsspaceapi.domain.Location;
import br.net.triangulohackerspace.thsspaceapi.service.LocationService;
import br.net.triangulohackerspace.thsspaceapi.service.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/locations")
public class LocationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocationController.class);

	private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    public Location createLocation(@RequestBody @Valid final Location location) {
        LOGGER.debug("Received request to create the {}", location);
        return locationService.save(location);
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public List<Location> listLocations() {
		LOGGER.debug("Received request to list all locations");
        return locationService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleLocationAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}
