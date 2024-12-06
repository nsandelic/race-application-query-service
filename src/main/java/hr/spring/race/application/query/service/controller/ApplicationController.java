package hr.spring.race.application.query.service.controller;

import hr.spring.race.application.query.service.model.entity.Application;
import hr.spring.race.application.query.service.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    private final ApplicationService applicationService;

    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping()
    public ResponseEntity<List<Application>> getAllApplications() {
        List<Application> applicationList = applicationService.getAllApplications();
        return new ResponseEntity<>(applicationList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable UUID id) {
        Application application = applicationService.getApplicationById(id);
        if(application != null)
            return new ResponseEntity<>(application, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }
}
