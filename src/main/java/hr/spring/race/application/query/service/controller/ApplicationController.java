package hr.spring.race.application.query.service.controller;

import hr.spring.race.application.query.service.model.entity.Application;
import hr.spring.race.application.query.service.security.JwtAuthenticationFilter;
import hr.spring.race.application.query.service.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http://localhost:4200")
public class ApplicationController {

    private static final Logger logger = Logger.getLogger(ApplicationController.class.getName());
    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping()
    public ResponseEntity<List<Application>> getAllApplications() {
        logger.info("ApplicationController - > getAllApplications()");
        List<Application> applicationList = applicationService.getAllApplications();
        logger.info("ApplicationController - > Returning: " + applicationList);
        return new ResponseEntity<>(applicationList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable UUID id) {
        logger.info("ApplicationController - > getApplicationById(), ID: " + id);
        Application application = applicationService.getApplicationById(id);
        if(application != null) {
            logger.info("ApplicationController - > Returning: " + application);
            return new ResponseEntity<>(application, HttpStatus.OK);
        }
        else {
            logger.info("ApplicationController - > Returning: User not found");
            return ResponseEntity.notFound().build();
        }
    }
}
