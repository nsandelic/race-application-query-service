package hr.spring.race.application.query.service.controller;

import hr.spring.race.application.query.service.model.entity.Race;
import hr.spring.race.application.query.service.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/races")
@CrossOrigin(origins = "http://localhost:4200")
public class RaceController {

    private static final Logger logger = Logger.getLogger(RaceController.class.getName());
    private final RaceService raceService;

    @Autowired
    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping()
    public ResponseEntity<List<Race>> getAllRaces() {
        logger.info("RaceController - > getAllRaces()");
        List<Race> raceList = raceService.getAllRaces();
        logger.info("RaceController - > Returning: " + raceList);
        return new ResponseEntity<>(raceList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Race> getRaceById(@PathVariable UUID id) {
        logger.info("RaceController - > getRaceById(), ID: " + id);
        Race race = raceService.getRaceById(id);
        if(race != null) {
            logger.info("RaceController - > Returning: " + race);
            return new ResponseEntity<>(race, HttpStatus.OK);
        }
        else {
            logger.info("RaceController - > Returning: Races not found" );
            return ResponseEntity.notFound().build();
        }
    }
}
