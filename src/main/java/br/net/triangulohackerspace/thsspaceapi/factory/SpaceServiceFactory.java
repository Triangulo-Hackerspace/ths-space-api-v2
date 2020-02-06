package br.net.triangulohackerspace.thsspaceapi.factory;

import br.net.triangulohackerspace.thsspaceapi.service.BusinessService;
import br.net.triangulohackerspace.thsspaceapi.service.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class SpaceServiceFactory<E, ID extends Serializable> {

    @Autowired
    private Collection<BusinessService<E,ID>> commonServices;

    private Map<Services, BusinessService<E,ID>> services;

    @PostConstruct
    private void init() {
        services = new HashMap<Services, BusinessService<E,ID>>();
        for (BusinessService<E,ID> commonService : commonServices) {
            services.put(commonService.appliesTo(), commonService);
        }
    }

    public BusinessService<E,ID> getService(Services listService) {
        return services.get(listService);
    }
}
