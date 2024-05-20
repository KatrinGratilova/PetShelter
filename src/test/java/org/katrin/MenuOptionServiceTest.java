package org.katrin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.io.ByteArrayInputStream;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.katrin.model.Pet;

public class MenuOptionServiceTest {
    private ByteArrayInputStream inContent;
    private int lastPetId;

    @Test
    public void testLeavePet_Ok(){
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
    public void testLeavePet_IncorrectAgeInput(){
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
    public void testLeavePet_IncorrectGenderInput(){
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
    public void takePet_Ok(){
        int expectedPetId = 2;
        String input = expectedPetId + "\n";

        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        lastPetId = 2;

        MenuOptionService menuService = new MenuOptionService();
        int actualPetId = menuService.takePet(System.out, lastPetId);

        assertEquals(expectedPetId, actualPetId);
    }

    @Test
    public void takePet_noPets(){
        int expectedPetId = -1; // status where there are no pets
        String input = expectedPetId + "\n";

        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        lastPetId = 0;

        MenuOptionService menuService = new MenuOptionService();
        int actualPetId = menuService.takePet(System.out, lastPetId);

        assertEquals(expectedPetId, actualPetId);
    }

    @Test
    public void takePet_returnToMenu(){
        int expectedPetId = 0; // status where there are no pets
        String input = expectedPetId + "\n";

        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
        lastPetId = 33;

        MenuOptionService menuService = new MenuOptionService();
        int actualPetId = menuService.takePet(System.out, lastPetId);

        assertEquals(expectedPetId, actualPetId);
    }

    @Test
    public void takePet_IncorrectIdInput(){
        lastPetId = 33;
        int expectedPetId = 2; // status where there are no pets
        String input = "df\n78\n-1\n" + expectedPetId + "\n";

        inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);


        MenuOptionService menuService = new MenuOptionService();
        int actualPetId = menuService.takePet(System.out, lastPetId);

        assertEquals(expectedPetId, actualPetId);
    }
}
