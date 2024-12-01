package hr.spring.race.application.query.service.model.enums;

import lombok.Getter;

@Getter
public enum Role {

    APPLICANT("Applicant"),
    ADMINISTRATOR("Administrator");

    private final String label;

    Role(String label) {
        this.label = label;
    }
}
