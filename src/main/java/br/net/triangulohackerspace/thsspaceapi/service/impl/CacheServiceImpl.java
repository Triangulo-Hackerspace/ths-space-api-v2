package br.net.triangulohackerspace.thsspaceapi.service.impl;

import br.net.triangulohackerspace.thsspaceapi.domain.Cache;
import br.net.triangulohackerspace.thsspaceapi.repository.CacheRepository;
import br.net.triangulohackerspace.thsspaceapi.service.CacheService;
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
public class CacheServiceImpl implements CacheService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheServiceImpl.class);
    private final CacheRepository repository;

    @Autowired
    public CacheServiceImpl(final CacheRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Cache save(@NotNull @Valid final Cache cache) {
        LOGGER.debug("Creating {}", cache);
        Optional<Cache> existing = repository.findById(cache.getId());
        if (!existing.isPresent()) {
            throw new AlreadyExistsException(
                    String.format("There already exists a cache with id=%s", cache.getId()));
        }
        return repository.save(cache);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cache> getList() {
		LOGGER.debug("Retrieving the list of all caches");
        return repository.findAll();
    }

}
