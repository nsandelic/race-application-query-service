package hr.spring.race.application.query.service.listener;

import hr.spring.race.application.query.service.model.entity.Application;
import hr.spring.race.application.query.service.model.event.ApplicationEvent;
import hr.spring.race.application.query.service.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.logging.Logger;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class ApplicationListener {

    private static final Logger logger = Logger.getLogger(ApplicationListener.class.getName());
    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationListener(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @MessageMapping("/applications")
    public void handleApplicationEvents(@RequestBody ApplicationEvent applicationEvent) {
        logger.info("ApplicationListener - > handleApplicationEvents(),Event: " + applicationEvent);

        String action = applicationEvent.getAction();
        Application application = applicationEvent.getApplication();

        if ("CREATE".equals(action)) {
            try{
                applicationRepository.save(application);
            }catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("DELETE".equals(action)) {
            try{
                applicationRepository.deleteById(application.getId());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
