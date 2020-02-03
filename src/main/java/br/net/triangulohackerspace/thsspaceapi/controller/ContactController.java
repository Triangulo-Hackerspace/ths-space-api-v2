package br.net.triangulohackerspace.thsspaceapi.controller;

import br.net.triangulohackerspace.thsspaceapi.domain.Contact;
import br.net.triangulohackerspace.thsspaceapi.service.ContactService;
import br.net.triangulohackerspace.thsspaceapi.service.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/contacts")
public class ContactController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ContactController.class);

	//Autowired
	//private SpaceServiceFactory<Contact, Long> spaceServiceFactory;

	private final ContactService contactService;

	@Autowired
	public ContactController(ContactService contactService) {
		this.contactService = contactService;
	}

	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public Contact createContact(@RequestBody @Valid final Contact contact) {
		LOGGER.debug("Received request to create the {}", contact);
		return contactService.save(contact);
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public List<Contact> listContacts() {
		LOGGER.debug("Received request to list all contacts");
		return contactService.getList();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleContactAlreadyExistsException(AlreadyExistsException e) {
		return e.getMessage();
	}

}
