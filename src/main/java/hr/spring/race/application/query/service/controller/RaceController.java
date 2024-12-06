package hr.spring.race.application.query.service.controller;

import hr.spring.race.application.query.service.model.entity.Race;
import hr.spring.race.application.query.service.service.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/races")
@CrossOrigin(origins = "http://localhost:4200")
public class RaceController {

    private final RaceService raceService;

    @Autowired
    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping()
    public ResponseEntity<List<Race>> getAllRaces() {
        List<Race> raceList = raceService.getAllRaces();
        return new ResponseEntity<>(raceList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Race> getRaceById(@PathVariable UUID id) {
        Race race = raceService.getRaceById(id);
        if(race != null)
            return new ResponseEntity<>(race, HttpStatus.OK);
        else
            return ResponseEntity.notFound().build();
    }
}
