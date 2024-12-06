package hr.spring.race.application.query.service.listener;

import hr.spring.race.application.query.service.model.entity.Race;
import hr.spring.race.application.query.service.model.event.RaceEvent;
import hr.spring.race.application.query.service.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class RaceEventListener {

    private final RaceRepository raceRepository;

    @Autowired
    public RaceEventListener(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @MessageMapping("/races")
    public void handleRaceEvent(@RequestBody RaceEvent  raceEvent) {

        String action = raceEvent.getAction();
        Race race = raceEvent.getRace();
        System.out.println("Received event: " + raceEvent.getAction() + " for race: " + raceEvent.getRace().getId());

        if ("CREATE".equals(action)) {
            try {
                raceRepository.save(race);
            }catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("UPDATE".equals(action)) {
            try {
                Optional<Race> existingRace = raceRepository.findById(race.getId());
                if (existingRace.isPresent()) {
                 raceRepository.save(race);
                }
            }catch (Exception e) {
            e.printStackTrace();
        }
        } else if ("DELETE".equals(action)) {
            try {
                raceRepository.deleteById(race.getId());
             }catch (Exception e) {
             e.printStackTrace();
             }
            }
    }
}
