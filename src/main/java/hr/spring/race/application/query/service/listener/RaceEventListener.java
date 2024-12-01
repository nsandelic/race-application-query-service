package hr.spring.race.application.query.service.listener;

import hr.spring.race.application.query.service.model.entity.Race;
import hr.spring.race.application.query.service.model.event.RaceEvent;
import hr.spring.race.application.query.service.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RaceEventListener {

    private final RaceRepository raceRepository;

    @Autowired
    public RaceEventListener(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @MessageMapping("/topic/race-events")
    public void handleRaceEvent(RaceEvent raceEvent) {
        String action = raceEvent.getAction();
        Race race = raceEvent.getRace();

        if ("CREATE".equals(action)) {
            raceRepository.save(race);
        } else if ("UPDATE".equals(action)) {
            Optional<Race> existingRace = raceRepository.findById(race.getId());
            if (existingRace.isPresent()) {
                raceRepository.save(race);
            }
        } else if ("DELETE".equals(action)) {
            raceRepository.deleteById(race.getId());
        }
    }
}
