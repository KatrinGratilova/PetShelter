package org.katrin;

import org.junit.Test;
import org.katrin.model.Gender;
import org.katrin.model.Pet;
import org.katrin.serializer.PetSerializer;
import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MenuOptionHandlerTest {
    Path petsFilePath = Path.of("src/main/resources/petsTest.json");
    private ByteArrayInputStream inContent;
    private final PetSerializer serializer = new PetSerializer(new JsonMapper());
    private int lastPetId;

    @Test
    public void addPet_Ok() {
        String input = "TestPet\nDog\nLabrador\n5\nMale\n";
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        File petsFile = petsFilePath.toFile();

        List<Pet> expectedPets = new ArrayList<>();
        lastPetId = 0;

        Pet addedPet = Pet.builder()
                .id(++lastPetId)
                .name("TestPet")
                .type("Dog")
                .breed("Labrador")
                .age(5)
                .gender(Gender.MALE)
                .build();
        expectedPets.add(addedPet);

        MenuOptionHandler menuHandler = new MenuOptionHandler(petsFilePath);
        menuHandler.addPet();
        List<Pet> actualPets = menuHandler.getPets();

        assertEquals(expectedPets, actualPets);
        petsFile.deleteOnExit();
    }

    @Test
    public void removePet_PetDeletion() throws IOException {
        String input = "1\n";
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        File petsFile = petsFilePath.toFile();

        lastPetId = 0;
        List<Pet> expectedPets = new ArrayList<>(List.of(Pet.builder()
                .id(++lastPetId)
                .name("TestPet")
                .type("Dog")
                .breed("Labrador")
                .age(5)
                .gender(Gender.MALE)
                .build()));

        serializer.serializeList(petsFile, expectedPets);

        MenuOptionHandler menuHandler = new MenuOptionHandler(petsFilePath);
        menuHandler.removePet();
        expectedPets.removeFirst();
        List<Pet> actualPets = menuHandler.getPets();

        assertEquals(expectedPets, actualPets);
        petsFile.deleteOnExit();
    }

    @Test
    public void removePet_ReturnToMenu() throws IOException {
        String input = "0\n"; // if user entered  0 tp return to menu
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        File petsFile = petsFilePath.toFile();

        lastPetId = 0;
        List<Pet> expectedPets = new ArrayList<>(List.of(Pet.builder()
                .id(++lastPetId)
                .name("TestPet")
                .type("Dog")
                .breed("Labrador")
                .age(5)
                .gender(Gender.MALE)
                .build()));

        serializer.serializeList(petsFile, expectedPets);

        MenuOptionHandler menuHandler = new MenuOptionHandler(petsFilePath);
        menuHandler.removePet();
        List<Pet> actualPets = menuHandler.getPets();

        assertEquals(expectedPets, actualPets);
        petsFile.deleteOnExit();
    }

    @Test
    public void removePet_NoPets() {
        File petsFile = petsFilePath.toFile();

        List<Pet> expectedPets = new ArrayList<>();

        MenuOptionHandler menuHandler = new MenuOptionHandler(petsFilePath);
        menuHandler.removePet();

        List<Pet> actualPets = menuHandler.getPets();
        assertEquals(expectedPets, actualPets);
        petsFile.deleteOnExit();
    }

    @Test
    public void removePet_InvalidInput() throws IOException {
        String input = "-3\nh\n90\n1\n";
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        File petsFile = petsFilePath.toFile();
        lastPetId = 0;
        List<Pet> expectedPets = new ArrayList<>(List.of(Pet.builder()
                .id(++lastPetId)
                .name("TestPet")
                .type("Dog")
                .breed("Labrador")
                .age(5)
                .gender(Gender.MALE)
                .build()));


        serializer.serializeList(petsFile, expectedPets);

        MenuOptionHandler menuHandler = new MenuOptionHandler(petsFilePath);
        menuHandler.removePet();
        expectedPets.removeFirst();
        List<Pet> actualPets = menuHandler.getPets();

        assertEquals(expectedPets, actualPets);
        petsFile.deleteOnExit();
    }

    @Test
    public void ExitMenu_Ok() throws IOException {
        lastPetId = 0;
        List<Pet> expectedPets = new ArrayList<>(List.of(Pet.builder()
                .id(++lastPetId)
                .name("TestPet")
                .type("Dog")
                .breed("Labrador")
                .age(5)
                .gender(Gender.MALE)
                .build()));

        File petsFile = petsFilePath.toFile();

        serializer.serializeList(petsFile, expectedPets);

        MenuOptionHandler menuHandler = new MenuOptionHandler(petsFilePath);
        menuHandler.exitMenu();
        List<Pet> actualPets = serializer.deserializeList(petsFile);

        assertEquals(expectedPets, actualPets);
        petsFile.deleteOnExit();
    }
}
