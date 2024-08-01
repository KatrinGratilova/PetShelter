package org.katrin;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.katrin.serializer.PetSerializer;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class PetShelterApplication {
    private final PrintStream out;
    private final Scanner scanner;
    private final MenuOptionService menuService;
    private final String PETS_FILE_PATH = "src/main/resources/pets.json";
    PetSerializer serializer = new PetSerializer(new JsonMapper());
    private final MenuOptionHandler optionHandler;


    public PetShelterApplication() {
        out = new PrintStream(System.out);
        scanner = new Scanner(System.in);
        menuService = new MenuOptionService(out, scanner);
        optionHandler  = new MenuOptionHandler(new File(PETS_FILE_PATH), serializer, menuService, out);
    }

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        PetShelterApplication shelterApp = new PetShelterApplication(); // TODO: bullshit
        int menuOption;
        boolean exit = false;
        do {
            shelterApp.out.println(Messages.MENU_OPTIONS.getMessage());
            menuOption = shelterApp.optionInput();

            switch (menuOption) {
                case 1 -> shelterApp.optionHandler.showPets();
                case 2 -> shelterApp.optionHandler.addPet();
                case 3 -> shelterApp.optionHandler.removePet();
                case 4 -> {
                    exit = true;
                    shelterApp.optionHandler.exitMenu();
                }
                default -> shelterApp.out.println(Messages.INVALID_INPUT.getMessage());
            }
        }
        while (!exit);
        shelterApp.out.close();
        shelterApp.scanner.close();
    }

    private int optionInput() {
        int menuOption;
        try {
            out.print("Choose an option: ");
            menuOption = Integer.parseInt(scanner.nextLine()); // TODO: do smth with next or nextInt
        } catch (NumberFormatException e) {
            return -1; // option = -1 will be proceeded in default switch case
        }
        return menuOption;
    }
}
