package br.net.triangulohackerspace.thsspaceapi.controller;

import br.net.triangulohackerspace.thsspaceapi.domain.IssueReportChannels;
import br.net.triangulohackerspace.thsspaceapi.service.IssueReportChannelsService;
import br.net.triangulohackerspace.thsspaceapi.service.exception.AlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/v1/issue-report-channels")
public class IssueReportChannelsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IssueReportChannelsController.class);

	private final IssueReportChannelsService issueReportChannelsService;

    @Autowired
    public IssueReportChannelsController(IssueReportChannelsService issueReportChannelsService) {
        this.issueReportChannelsService = issueReportChannelsService;
    }

    @RequestMapping(value = "/issueReportChannels", method = RequestMethod.POST)
    public IssueReportChannels createIssueReportChannels(@RequestBody @Valid final IssueReportChannels issueReportChannels) {
        LOGGER.debug("Received request to create the {}", issueReportChannels);
        return issueReportChannelsService.save(issueReportChannels);
    }

    @RequestMapping(value = "/issueReportChannels", method = RequestMethod.GET)
    public List<IssueReportChannels> listIssueReportChannelss() {
		LOGGER.debug("Received request to list all issueReportChannelss");
        return issueReportChannelsService.getList();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleIssueReportChannelsAlreadyExistsException(AlreadyExistsException e) {
        return e.getMessage();
    }

}
