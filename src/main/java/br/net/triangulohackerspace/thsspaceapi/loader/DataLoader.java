package br.net.triangulohackerspace.thsspaceapi.loader;

import br.net.triangulohackerspace.thsspaceapi.domain.*;
import br.net.triangulohackerspace.thsspaceapi.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import static br.net.triangulohackerspace.thsspaceapi.util.DateUtil.getAtualDate;

@Component
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final CacheRepository cacheRepository;
    private final ContactRepository contactRepository;
    private final IssueReportChannelsRepository issueReportChannelsRepository;
    private final LocationRepository locationRepository;
    private final ProjectRepository projectRepository;
    private final SensorRepository sensorRepository;
    private final  SpacefedRepository spacefedRepository;
    private final SpaceRepository spaceRepository;
    private final StateRepository stateRepository;
    private final TemperatureRepository temperatureRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, CacheRepository cacheRepository, ContactRepository contactRepository, IssueReportChannelsRepository issueReportChannelsRepository, LocationRepository locationRepository, ProjectRepository projectRepository, SensorRepository sensorRepository, SpacefedRepository spacefedRepository, SpaceRepository spaceRepository, StateRepository stateRepository, TemperatureRepository temperatureRepository) {
        this.userRepository = userRepository;
        this.cacheRepository = cacheRepository;
        this.contactRepository = contactRepository;
        this.issueReportChannelsRepository = issueReportChannelsRepository;
        this.locationRepository = locationRepository;
        this.projectRepository = projectRepository;
        this.sensorRepository = sensorRepository;
        this.spacefedRepository = spacefedRepository;
        this.spaceRepository = spaceRepository;
        this.stateRepository = stateRepository;
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Space space = new Space("0.13", "The space name",
                "http://your-space.com/logo.png", "http://example.com");
        spaceRepository.save(space);

        Cache cache = new Cache("m.02", space);
        cacheRepository.save(cache);

        Contact contact = new Contact("e@xample.com", "@example",
                "irc://irc.freenode.net/example", "public@lists.example.com",
                "ZUB4YW1wbGUuY29tCg==", space);
        contactRepository.save(contact);

        IssueReportChannels issueReportChannels = new IssueReportChannels(
                "issue_mail", space);
        issueReportChannelsRepository.save(issueReportChannels);

        Location location = new Location("see the documentation", 39.240431,
                5.973817, space);
        locationRepository.save(location);

        Project project = new Project("example", "http://github.com/example",
                space);
        projectRepository.save(project);

        Project project2 = new Project("example2", "http://github.com/example2",
                space);
        projectRepository.save(project2);

        Sensor sensor = new Sensor("t1", space);
        sensorRepository.save(sensor);

        Temperature temperature1 = new Temperature();
        temperature1.setLocation("Roof");
        temperature1.setUnit("°C");
        temperature1.setValue("-");
        temperature1.setSensor(sensor);
        temperatureRepository.save(temperature1);

        Temperature temperature2 = new Temperature();
        temperature2.setLocation("Lab");
        temperature2.setUnit("°De");
        temperature2.setValue("-");
        temperature2.setSensor(sensor);
        temperatureRepository.save(temperature2);

        Spacefed spacefed = new Spacefed(false, false, false, space);
        spacefedRepository.save(spacefed);

        User user = new User("rogerio", "sena");
        userRepository.save(user);

	/*	State state1 = new State(true, space, user, getPlusDateByDay(-2),
				StateStatus.CLOSE.getStateStatus());
		stateRepository.save(state1);*/

        State state2 = new State(StateStatus.OPEN.getStatus(), space, user, getAtualDate());
        stateRepository.save(state2);
    }
}
