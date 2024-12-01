package hr.spring.race.application.query.service.model.enums;

import lombok.Getter;

@Getter
public enum RaceDistance {

    FIVE_KM("5k"),
    TEN_KM("10k"),
    HALF_MARATHON("HalfMarathon"),
    MARATHON("Marathon");

    private final String label;

    RaceDistance(String label) {
        this.label = label;
    }

}
