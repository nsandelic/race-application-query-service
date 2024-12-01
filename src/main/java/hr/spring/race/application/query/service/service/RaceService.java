package hr.spring.race.application.query.service.service;

import hr.spring.race.application.query.service.model.entity.Race;

import java.util.List;
import java.util.UUID;

public interface RaceService {

    List<Race> getAllRaces();
    Race getRaceById(UUID id);
}
