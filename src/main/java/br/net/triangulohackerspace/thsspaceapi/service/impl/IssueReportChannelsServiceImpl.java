package br.net.triangulohackerspace.thsspaceapi.service.impl;

import br.net.triangulohackerspace.thsspaceapi.domain.IssueReportChannels;
import br.net.triangulohackerspace.thsspaceapi.repository.IssueReportChannelsRepository;
import br.net.triangulohackerspace.thsspaceapi.service.IssueReportChannelsService;
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
public class IssueReportChannelsServiceImpl implements IssueReportChannelsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IssueReportChannelsServiceImpl.class);
    private final IssueReportChannelsRepository repository;

    @Autowired
    public IssueReportChannelsServiceImpl(final IssueReportChannelsRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public IssueReportChannels save(@NotNull @Valid final IssueReportChannels issueReportChannels) {
        LOGGER.debug("Creating {}", issueReportChannels);
        Optional<IssueReportChannels> existing = repository.findById(issueReportChannels.getId());
        if (!existing.isPresent()) {
            throw new AlreadyExistsException(
                    String.format("There already exists a issueReportChannels with id=%s", issueReportChannels.getId()));
        }
        return repository.save(issueReportChannels);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IssueReportChannels> getList() {
		LOGGER.debug("Retrieving the list of all issueReportChannelss");
        return repository.findAll();
    }

}
