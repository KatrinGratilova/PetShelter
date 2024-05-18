package org.katrin;

import lombok.Getter;

@Getter
public enum Messages {
    INVALID_INPUT("Invalid input. Try again!"),
    NO_SUCH_PET("There is no pet with this id!"),
    MENU_OPTIONS("""
                 
                 ------MENU------
                 1. View all pets;
                 2. Give up a pet;
                 3. Adopt a pet;
                 4. Exit.""");

    private final String message;

    Messages(String message){
        this.message = message;
    }
}
