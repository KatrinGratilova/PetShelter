package org.katrin;

import org.katrin.model.Gender;
import org.katrin.model.Pet;

import java.io.PrintStream;
import java.util.Scanner;

public class MenuOptionService {
    private final PrintStream out;
    private final Scanner in;

    public MenuOptionService(PrintStream out, Scanner in) {
        this.out = out;
        this.in = in;
    }

    public Pet leavePet(int lastPetId) {
        Pet.PetBuilder builder = Pet.builder();
        builder.id(lastPetId + 1);

        out.println(Messages.FILL_IN_PET_INFO.getMessage());
        out.print(Messages.NAME.getMessage());
        builder.name(in.nextLine());
        out.print(Messages.TYPE.getMessage());
        builder.type(in.nextLine());
        out.print(Messages.BREED.getMessage());
        builder.breed(in.nextLine());

        builder.age(ageInput());
        builder.gender(genderInput());

        return builder.build();
    }

    private int ageInput() {
        int age = -1;
        do {
            try {
                out.print(Messages.AGE.getMessage());
                age = Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                out.println(Messages.INVALID_INPUT.getMessage());
            }
        } while (age < 0); // check that age is not negative
        return age;
    }

    private Gender genderInput() {
        Gender gender = null;
        do {
            out.print(Messages.GENDER.getMessage());
            String inputGender = in.nextLine();
            if (inputGender.equalsIgnoreCase(Gender.MALE.getMessage()))
                gender = Gender.MALE;
            else if (inputGender.equalsIgnoreCase(Gender.FEMALE.getMessage()))
                gender = Gender.FEMALE;
            else
                out.println(Messages.INVALID_INPUT.getMessage());
        } while (gender == null);
        return gender;
    }

    public int takePet(int lastPetId) {
        int petId = -1;
        if (lastPetId == 0) {
            out.println(Messages.NO_PETS.getMessage());
            return petId;
        }
        do {
            try {
                out.print(Messages.ENTER_PET_ID.getMessage());
                petId = Integer.parseInt(in.nextLine());

                if (petId == 0)
                    out.println(Messages.RETURNED_TO_MENU.getMessage());
            } catch (NumberFormatException e) {
                out.println(Messages.INVALID_INPUT.getMessage());
            }
        } while (petId < 0 || petId > lastPetId);  // check that id isn't negative and isn't bigger than last id in list
        return petId;
    }
}
