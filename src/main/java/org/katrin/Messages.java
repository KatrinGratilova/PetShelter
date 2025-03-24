package org.katrin;


public enum Messages {
    INVALID_INPUT("Invalid input. Try again!"),
    NO_SUCH_PET("There is no pet with this id!"),
    IO_EXCEPTION("An IO exception occurred."),
    NO_PETS("There are no pets in shelter!"),
    PET_WAS_ADDED("The pet was added to pet shelter!"),
    ALL_PETS("-- All pets in shelter --"),
    GOODBYE("Goodbye!"),
    PET_WAS_ADOPTED("Pet №%d was adopted!"),
    FILL_IN_PET_INFO("Fill in your pet information:"),
    ENTER_PET_ID("Enter pet id (0 to return to menu): "),
    RETURNED_TO_MENU("You returned to menu."),
    NAME("Name: "),
    TYPE("Type: "),
    BREED("Breed: "),
    AGE("Age: "),
    GENDER("Gender (male/female): "),


    MENU_OPTIONS("""
                             
            ------ MENU ------
            1. View all pets;
            2. Give up a pet;
            3. Adopt a pet;
            4. Exit.
            """);

    public String getMessage() {
        return message;
    }

    private final String message;

    Messages(String message) {
        this.message = message;
    }

}
