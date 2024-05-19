package org.katrin;

import org.katrin.model.Gender;
import org.katrin.model.Pet;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class MenuOptionService {
    private final Scanner scanner;

    public MenuOptionService(){
        this.scanner = new Scanner(System.in);
    }

    Pet leavePet(PrintStream out, List<Pet> pets) {
        Pet.PetBuilder builder = Pet.builder();
        builder.id(getLastPetId(pets) + 1);

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

    int getLastPetId(List<Pet> pets){
        if (!pets.isEmpty())
            return pets.getLast().getId();
        else
            return 0;
    }

    private int ageInput(PrintStream out){
        int age = - 1;
        do {
            try {
                out.print("Age: ");
                age = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                out.println(Messages.INVALID_INPUT.getMessage());
            }
        } while (age < 0);
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

    int takePet(PrintStream out) {
        int petId = -1;
        do {
            try {
                out.print("Enter pet id (0 to return to menu): ");
                petId = Integer.parseInt(scanner.nextLine());

                if (petId == 0) {
                    out.println("You returned to menu.");
                    break;
                }
            } catch (NumberFormatException e) {
                out.println(Messages.INVALID_INPUT.getMessage());
            }
        } while (petId < 0);
        return petId;
    }
}
