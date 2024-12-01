package hr.spring.race.application.query.service.listener;

import hr.spring.race.application.query.service.model.entity.Application;
import hr.spring.race.application.query.service.model.event.ApplicationEvent;
import hr.spring.race.application.query.service.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationListener {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationListener(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @MessageMapping("/topic/application-events")
    public void handleApplicationEvents(ApplicationEvent applicationEvent) {
        String action = applicationEvent.getAction();
        Application application = applicationEvent.getApplication();

        if ("CREATE".equals(action)) {
            applicationRepository.save(application);
        } else if ("UPDATE".equals(action)) {
            Optional<Application> existingApplication = applicationRepository.findById(application.getId());
            if (existingApplication.isPresent()) {
                applicationRepository.save(application);
            }
        } else if ("DELETE".equals(action)) {
            applicationRepository.deleteById(application.getId());
        }
    }
}
