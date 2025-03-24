package org.katrin.model;

import lombok.Getter;


public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    public String getMessage() {
        return message;
    }

    private final String message;

    Gender(String message) {
        this.message = message;
    }
}
