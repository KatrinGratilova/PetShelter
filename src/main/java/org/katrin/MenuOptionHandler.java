package org.katrin;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.katrin.serializer.PetSerializer;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MenuOptionHandler {
    private List<Pet> pets;
    private final PetService petService;
    private final PrintStream out;
    private final PetSerializer serializer;
    private final Path PETS_FILE_PATH = Path.of("src/main/resources/pets.json");
    private final File petsFile;

    public MenuOptionHandler(){
        petService = new PetService();
        out = new PrintStream(System.out);
        petsFile = PETS_FILE_PATH.toFile();
        serializer = new PetSerializer(new JsonMapper());
        pets = getAllPetsFromFile();
    }

    void addPet(){
        pets.add(petService.leavePet(out, pets));
        out.println("The pet was added to pet shelter!");
    }

    void removePet(){
        int petId = petService.takePet(out);
        Pet petToDelete = null;
        for (Pet pet : pets) {
            if (pet.getId() == petId) {
                petToDelete = pet;
            }
        }
        if (petToDelete != null) {
            pets.remove(petToDelete);
            out.println("Pet №" + petId + " was adopted!");
        } else
            out.println(Messages.NO_SUCH_PET.getMessage());
    }

    void showPets() {
        out.println("-----------ALL PETS IN SHELTER-----------");
        pets.forEach(pet -> out.println(pet.toString()));
    }

    void exitMenu(){
        savePetsToFile();
        out.println("Goodbye!");
    }

    void savePetsToFile(){
        try {
            serializer.serializeList(petsFile, pets);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    List<Pet> getAllPetsFromFile(){
        try {
            if (petsFile.exists())
                return serializer.deserializeList(petsFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>();
    }
}
