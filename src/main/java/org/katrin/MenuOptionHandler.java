package org.katrin;

import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.Getter;
import org.katrin.model.Pet;
import org.katrin.serializer.PetSerializer;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MenuOptionHandler {
    @Getter
    private final List<Pet> pets;
    private final MenuOptionService menuService;
    private final PrintStream out;
    private final PetSerializer serializer;
    private final File petsFile;

    public MenuOptionHandler(Path petsFilePath) {
        menuService = new MenuOptionService();
        out = new PrintStream(System.out);
        petsFile = petsFilePath.toFile();
        serializer = new PetSerializer(new JsonMapper());
        pets = getAllPetsFromFile();
    }

    public void addPet() {
        pets.add(menuService.leavePet(out, getLastPetId()));
        out.println("The pet was added to pet shelter!");
    }

    public void removePet() {
        int petId = menuService.takePet(out, getLastPetId());
        if (petId == 0 || petId == -1) // if client decided to return to menu or there are no pets
            return;

        Pet petToDelete = pets.stream().filter(p -> p.getId() == petId).findFirst().orElse(null);
        if (petToDelete != null) {
            pets.remove(petToDelete);
            out.println("Pet №" + petId + " was adopted!");
        } else
            out.println(Messages.NO_SUCH_PET.getMessage());
    }

    private int getLastPetId(){
        // if there are no pets, return 0
        return !pets.isEmpty() ? pets.getLast().getId() : 0;
    }

    public void showPets() {
        out.println("--All pets in shelter--");
        if (!pets.isEmpty())
            pets.forEach(pet -> out.println(pet.toString()));
        else
            out.println(Messages.NO_PETS.getMessage());
    }

    public void exitMenu() {
        savePetsToFile();
        out.println("Goodbye!");
    }

    private void savePetsToFile() {
        try {
            serializer.serializeList(petsFile, pets);
        } catch (IOException e) {
            out.println(Messages.IO_EXCEPTION.getMessage());
        }
    }

    private List<Pet> getAllPetsFromFile() {
        try {
            if (petsFile.exists())
                return serializer.deserializeList(petsFile);
        } catch (IOException e) {
            out.println(Messages.IO_EXCEPTION.getMessage());
        }
        return new ArrayList<>();
    }
}
