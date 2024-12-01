package hr.spring.race.application.query.service.service;

import hr.spring.race.application.query.service.model.entity.Application;

import java.util.List;
import java.util.UUID;

public interface ApplicationService {

    List<Application> getAllApplications();
    Application getApplicationById(UUID id);

}
