package org.katrin.model;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private final String message;

    Gender(String message) {
        this.message = message;
    }
}
