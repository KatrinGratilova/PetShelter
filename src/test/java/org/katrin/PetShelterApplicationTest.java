package org.katrin;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;

public class PetShelterApplicationTest {
    @Test
    public void run_NumberFormatException() {
        String input = "hj\nt\n4\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        PetShelterApplication petShelter = new PetShelterApplication();
        petShelter.run();
    }

    @Test
    public void run_OptionDoesNotExists() {
        String input = "-4\n9\n0\n4";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        PetShelterApplication petShelter = new PetShelterApplication();
        petShelter.run();
    }
}
