package org.katrin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.ByteArrayInputStream;

import org.junit.Test;
import org.katrin.model.Pet;

public class MenuOptionServiceTest {
    private ByteArrayInputStream inContent;
    private int lastPetId;

    @Test
    public void testLeavePet_Ok() {
        String input = "Test\nDog\nLabrador\n5\nMale\n";
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        MenuOptionService menuOptionService = new MenuOptionService();
        lastPetId = 0;
        Pet pet = menuOptionService.leavePet(System.out, lastPetId);

        assertNotNull(pet);
        assertEquals(lastPetId + 1, pet.getId());
        assertEquals("Test", pet.getName());
        assertEquals("Dog", pet.getType());
        assertEquals("Labrador", pet.getBreed());
        assertEquals(5, pet.getAge());
        assertEquals("Male", pet.getGender().getMessage());
    }

    @Test
    public void testLeavePet_InvalidAgeInput() {
        String input = "Test\nDog\nLabrador\n-3\nf\n4\nFemale\n";
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        MenuOptionService menuOptionService = new MenuOptionService();
        lastPetId = 0;
        Pet pet = menuOptionService.leavePet(System.out, lastPetId);

        assertNotNull(pet);
        assertEquals(lastPetId + 1, pet.getId());
        assertEquals("Test", pet.getName());
        assertEquals("Dog", pet.getType());
        assertEquals("Labrador", pet.getBreed());
        assertEquals(4, pet.getAge());
        assertEquals("Female", pet.getGender().getMessage());
    }

    @Test
    public void testLeavePet_InvalidGenderInput() {
        String input = "Test\nDog\nLabrador\n4\nboy\nmal\nmale\n";
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        MenuOptionService menuOptionService = new MenuOptionService();
        lastPetId = 0;
        Pet pet = menuOptionService.leavePet(System.out, lastPetId);

        assertNotNull(pet);
        assertEquals(lastPetId + 1, pet.getId());
        assertEquals("Test", pet.getName());
        assertEquals("Dog", pet.getType());
        assertEquals("Labrador", pet.getBreed());
        assertEquals(4, pet.getAge());
        assertEquals("Male", pet.getGender().getMessage());
    }

    @Test
    public void takePet_Ok() {
        int expectedPetId = 1;

        String input = expectedPetId + "\n";
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        MenuOptionService menuService = new MenuOptionService();
        lastPetId = 2;
        int actualPetId = menuService.takePet(System.out, lastPetId);

        assertEquals(expectedPetId, actualPetId); // 1
    }

    @Test
    public void takePet_noPets() {
        String input = 2 + "\n";
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        MenuOptionService menuService = new MenuOptionService();
        lastPetId = 0;
        int expectedPetId = -1; // status where there are no pets
        int actualPetId = menuService.takePet(System.out, lastPetId);

        assertEquals(expectedPetId, actualPetId); // -1
    }

    @Test
    public void takePet_returnToMenu() {
        int expectedPetId = 0; // status where there are no pets

        String input = expectedPetId + "\n";
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        MenuOptionService menuService = new MenuOptionService();
        lastPetId = 4;
        int actualPetId = menuService.takePet(System.out, lastPetId);

        assertEquals(expectedPetId, actualPetId); // 0
    }

    @Test
    public void takePet_InvalidIdInput() {
        int expectedPetId = 2;

        String input = "df\n78\n-1\n" + expectedPetId + "\n";
        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);

        MenuOptionService menuService = new MenuOptionService();
        lastPetId = 4;
        int actualPetId = menuService.takePet(System.out, lastPetId);

        assertEquals(expectedPetId, actualPetId); // 2
    }
}
