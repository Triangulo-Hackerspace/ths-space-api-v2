package br.net.triangulohackerspace.thsspaceapi.controller;

import br.net.triangulohackerspace.thsspaceapi.domain.User;
import br.net.triangulohackerspace.thsspaceapi.service.UserService;
import br.net.triangulohackerspace.thsspaceapi.service.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/users")
public class UserController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserController.class);

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User createUser(@RequestBody @Valid final User user) {
		LOGGER.debug("Received request to create the {}", user);
		return userService.save(user);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<User> listUsers() {
		LOGGER.debug("Received request to list all users");
		return userService.getList();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleUserAlreadyExistsException(AlreadyExistsException e) {
		return e.getMessage();
	}

}
