package br.net.triangulohackerspace.thsspaceapi.service.impl;

import br.net.triangulohackerspace.thsspaceapi.domain.Contact;
import br.net.triangulohackerspace.thsspaceapi.repository.ContactRepository;
import br.net.triangulohackerspace.thsspaceapi.service.ContactService;
import br.net.triangulohackerspace.thsspaceapi.service.Services;
import br.net.triangulohackerspace.thsspaceapi.service.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactServiceImpl.class);
    private final ContactRepository repository;

    @Autowired
    public ContactServiceImpl(final ContactRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Contact save(@NotNull @Valid final Contact Contact) {
        LOGGER.debug("Creating {}", Contact);
        Optional<br.net.triangulohackerspace.thsspaceapi.domain.Contact> existing = repository.findById(Contact.getId());
        if (!existing.isPresent()) {
            throw new AlreadyExistsException(
                    String.format("There already exists a Contact with id=%s", Contact.getId()));
        }
        return repository.save(Contact);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contact> getList() {
        LOGGER.debug("Retrieving the list of all Contacts");
        return repository.findAll();
    }

    @Override
    public Services appliesTo() {
        return null;
    }
}
