package hr.spring.race.application.query.service.listener;

import hr.spring.race.application.query.service.model.entity.Application;
import hr.spring.race.application.query.service.model.event.ApplicationEvent;
import hr.spring.race.application.query.service.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


@Controller
@CrossOrigin(origins = "http://localhost:4200")
public class ApplicationListener {

    private final ApplicationRepository applicationRepository;

    @Autowired
    public ApplicationListener(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    @MessageMapping("/applications")
    public void handleApplicationEvents(@RequestBody ApplicationEvent applicationEvent) {

        String action = applicationEvent.getAction();
        Application application = applicationEvent.getApplication();
        System.out.println("Received event: " + action + " for application: " + application.getId());
        System.out.println("CREATE".equals(action));

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
