package org.katrin.serializer;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.Test;
import org.katrin.model.Gender;
import org.katrin.model.Pet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PetSerializerTest {
    PetSerializer serializer = new PetSerializer(new JsonMapper());
    Path petsFilePath = Path.of("src/main/resources/petsTest.json");

    @Test
    public void serializeList_Ok() throws IOException {
        int lastPetId = 0;
        List<Pet> expectedPets = new ArrayList<>(List.of(Pet.builder()
                        .id(++lastPetId)
                        .name("Bobik")
                        .type("Dog")
                        .breed("Labrador")
                        .age(5)
                        .gender(Gender.FEMALE)
                        .build(),
                Pet.builder()
                        .id(++lastPetId)
                        .name("Gray")
                        .type("Cat")
                        .breed("Labrador")
                        .age(6)
                        .gender(Gender.MALE)
                        .build()));

        File petsFile = petsFilePath.toFile();
        serializer.serializeList(petsFile, expectedPets);
        List<Pet> actualPets = serializer.deserializeList(petsFile);

        assertTrue(petsFile.exists());
        assertEquals(expectedPets, actualPets);
        petsFile.deleteOnExit();
    }

    @Test
    public void deserializeList_Ok() throws IOException {
        int lastPetId = 0;
        List<Pet> expectedPets = new ArrayList<>(List.of(Pet.builder()
                        .id(++lastPetId)
                        .name("Bobik")
                        .type("Dog")
                        .breed("Labrador")
                        .age(5)
                        .gender(Gender.FEMALE)
                        .build(),
                Pet.builder()
                        .id(++lastPetId)
                        .name("Gray")
                        .type("Cat")
                        .breed("Labrador")
                        .age(6)
                        .gender(Gender.MALE)
                        .build()));

        File petsFile = petsFilePath.toFile();
        serializer.serializeList(petsFile, expectedPets);
        List<Pet> actualPets = serializer.deserializeList(petsFile);

        assertTrue(petsFile.exists());
        assertEquals(expectedPets, actualPets);
        petsFile.deleteOnExit();
    }
}
