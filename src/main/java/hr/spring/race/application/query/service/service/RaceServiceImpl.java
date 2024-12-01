package hr.spring.race.application.query.service.service;

import hr.spring.race.application.query.service.model.entity.Race;
import hr.spring.race.application.query.service.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RaceServiceImpl implements RaceService {

    private final RaceRepository raceRepository;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    @Override
    public List<Race> getAllRaces() {
        return raceRepository.findAll();
    }

    @Override
    public Race getRaceById(UUID id) {
        return raceRepository.findById(id).orElse(null);
    }
}
