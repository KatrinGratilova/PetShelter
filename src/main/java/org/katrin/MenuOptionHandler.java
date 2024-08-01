package org.katrin;

import lombok.Getter;
import org.katrin.model.Pet;
import org.katrin.serializer.PetSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MenuOptionHandler {
    private final File petsFile;
    private final PetSerializer serializer;
    private final MenuOptionService menuService;
    private final PrintStream out;
    @Getter
    private final List<Pet> pets;

    public MenuOptionHandler(File petsFile, PetSerializer serializer, MenuOptionService menuService, PrintStream out) {
        this.petsFile = petsFile;
        this.serializer = serializer;
        this.menuService = menuService;
        this.out = out;
        pets = getAllPetsFromFile();
    }

    public void addPet() {
        pets.add(menuService.leavePet(getLastPetId()));
        out.println(Messages.PET_WAS_ADDED.getMessage());
    }

    public void removePet() {
        int petId = menuService.takePet(getLastPetId());
        if (petId == 0 || petId == -1) // if client decided to return to menu or there are no pets
            return;

        Pet toDelete = pets.stream().filter(p -> p.getId() == petId).findFirst().orElse(null);
        if (toDelete != null) {
            pets.remove(toDelete);
            out.printf(Messages.PET_WAS_ADOPTED.getMessage(), petId);
        } else
            out.println(Messages.NO_SUCH_PET.getMessage());
    }

    private int getLastPetId() {
        // if there are no pets, return 0
        return !pets.isEmpty() ? pets.getLast().getId() : 0;
    }

    public void showPets() {
        out.println(Messages.ALL_PETS.getMessage());
        if (!pets.isEmpty())
            pets.forEach(pet -> out.println(pet.toString()));
        else
            out.println(Messages.NO_PETS.getMessage());
    }

    public void exitMenu() {
        savePetsToFile();
        out.println(Messages.GOODBYE.getMessage());
        out.close();
    }

    private void savePetsToFile() {
        try {
            serializer.serializeList(petsFile, pets);
        } catch (IOException e) {
            out.println(Messages.IO_EXCEPTION.getMessage()); // TODO: throw own exception
        }
    }

    private List<Pet> getAllPetsFromFile() {
        try {
            if (petsFile.exists())
                return serializer.deserializeList(petsFile);
        } catch (IOException e) {
            out.println(Messages.IO_EXCEPTION.getMessage()); // TODO: throw own exception
        }
        return new ArrayList<>();
    }
}
