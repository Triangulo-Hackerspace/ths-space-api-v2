package br.net.triangulohackerspace.thsspaceapi.controller;

import br.net.triangulohackerspace.thsspaceapi.domain.Sensor;
import br.net.triangulohackerspace.thsspaceapi.service.SensorService;
import br.net.triangulohackerspace.thsspaceapi.service.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/sensors")
public class SensorController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SensorController.class);

	private final SensorService sensorService;

	@Autowired
	public SensorController(SensorService sensorService) {
		this.sensorService = sensorService;
	}

	@RequestMapping(value = "/sensor", method = RequestMethod.POST)
	public Sensor createSensor(@RequestBody @Valid final Sensor sensor) {
		LOGGER.debug("Received request to create the {}", sensor);
		return sensorService.save(sensor);
	}

	@RequestMapping(value = "/sensor", method = RequestMethod.GET)
	public List<Sensor> listSensors() {
		LOGGER.debug("Received request to list all sensors");
		return sensorService.getList();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleSensorAlreadyExistsException(AlreadyExistsException e) {
		return e.getMessage();
	}

}
