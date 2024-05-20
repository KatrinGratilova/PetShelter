package org.katrin;

import org.katrin.model.Gender;
import org.katrin.model.Pet;

import java.io.PrintStream;
import java.util.Scanner;

public class MenuOptionService {
    private final Scanner scanner;

    public MenuOptionService(){
        this.scanner = new Scanner(System.in);
    }

    public Pet leavePet(PrintStream out, int lastPetId) {
        Pet.PetBuilder builder = Pet.builder();
        builder.id(lastPetId + 1);

        out.println("Fill in your pet information:");
        out.print("Name: ");
        builder.name(scanner.nextLine());
        out.print("Type: ");
        builder.type(scanner.nextLine());
        out.print("Breed: ");
        builder.breed(scanner.nextLine());

        builder.age(ageInput(out));
        builder.gender(genderInput(out));

        return builder.build();
    }

    private int ageInput(PrintStream out){
        int age = -1;
        do {
            try {
                out.print("Age: ");
                age = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                out.println(Messages.INVALID_INPUT.getMessage());
            }
        } while (age < 0); // check that age is not negative
        return age;
    }

    private Gender genderInput(PrintStream out){
        Gender gender = null;
        do {
            out.print("Gender (male/female): ");
            String inputGender = scanner.nextLine();
            if (inputGender.equalsIgnoreCase("male"))
                gender = Gender.MALE;
            else if (inputGender.equalsIgnoreCase("female"))
                gender = Gender.FEMALE;
            else
                out.println(Messages.INVALID_INPUT.getMessage());
        } while (gender == null);
        return gender;
    }

    public int takePet(PrintStream out, int lastPetId) {
        int petId = -1;
        if (lastPetId == 0) {
            out.println(Messages.NO_PETS.getMessage());
            return petId;
        }
        do {
            try {
                out.print("Enter pet id (0 to return to menu): ");
                petId = Integer.parseInt(scanner.nextLine());

                if (petId == 0) {
                    out.println("You returned to menu.");
                }
            } catch (NumberFormatException e) {
                out.println(Messages.INVALID_INPUT.getMessage());
            }
        } while (petId < 0 || petId > lastPetId);  // check that id isn't negative and isn't bigger than last id in list
        return petId;
    }
}
