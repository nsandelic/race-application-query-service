package hr.spring.race.application.query.service.service;

import hr.spring.race.application.query.service.model.entity.Application;
import hr.spring.race.application.query.service.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private static final Logger logger = Logger.getLogger(ApplicationServiceImpl.class.getName());

    private ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }


    @Override
    public List<Application> getAllApplications() {
        logger.info("ApplicationServiceImpl - > getAllApplications()");
        return applicationRepository.findAll();
    }

    @Override
    public Application getApplicationById(UUID id) {
        logger.info("ApplicationServiceImpl - > getApplicationById()");
        return applicationRepository.findById(id).orElse(null);
    }
}
