package org.katrin;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.katrin.model.Pet;
import org.katrin.serializer.PetSerializer;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MenuOptionHandler {
    private final List<Pet> pets;
    private final MenuOptionService menuService;
    private final PrintStream out;
    private final PetSerializer serializer;
    private final Path PETS_FILE_PATH = Path.of("src/main/resources/pets.json");
    private final File petsFile;

    public MenuOptionHandler(){
        menuService = new MenuOptionService();
        out = new PrintStream(System.out);
        petsFile = PETS_FILE_PATH.toFile();
        serializer = new PetSerializer(new JsonMapper());
        pets = getAllPetsFromFile();
    }

    public void addPet(){
        pets.add(menuService.leavePet(out, pets));
        out.println("The pet was added to pet shelter!");
    }

    public void removePet(){
        int petId = menuService.takePet(out);
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

    public void showPets() {
        out.println("------ALL PETS IN SHELTER------");
        pets.forEach(pet -> out.println(pet.toString()));
    }

    public void exitMenu(){
        savePetsToFile();
        out.println("Goodbye!");
    }

    private void savePetsToFile(){
        try {
            serializer.serializeList(petsFile, pets);
        } catch (IOException e) {
            out.println(Messages.IO_EXCEPTION.getMessage());
        }
    }

    private List<Pet> getAllPetsFromFile(){
        try {
            if (petsFile.exists())
                return serializer.deserializeList(petsFile);
        } catch (IOException e) {
            out.println(Messages.IO_EXCEPTION.getMessage());
        }
        return new ArrayList<>();
    }
}