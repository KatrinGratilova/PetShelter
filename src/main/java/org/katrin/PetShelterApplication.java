package org.katrin;

import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Scanner;

public class PetShelterApplication {
    private final PrintStream out;
    private final Scanner scanner;
    private final MenuOptionHandler optionHandler;
    private final Path PETS_FILE_PATH = Path.of("src/main/resources/pets.json");

    public PetShelterApplication() {
        out = new PrintStream(System.out);
        scanner = new Scanner(System.in);
        optionHandler = new MenuOptionHandler(PETS_FILE_PATH);
    }

    public static void main(String[] args) {
        PetShelterApplication shelterApp = new PetShelterApplication();
        shelterApp.run();
    }

    public void run() {
        int menuOption;
        boolean exit = false;
        do {
            out.println(Messages.MENU_OPTIONS.getMessage());
            menuOption = optionInput();

            switch (menuOption) {
                case 1:
                    optionHandler.showPets();
                    break;
                case 2:
                    optionHandler.addPet();
                    break;
                case 3:
                    optionHandler.removePet();
                    break;
                case 4:
                    exit = true;
                    optionHandler.exitMenu();
                    break;
                default:
                    out.println(Messages.INVALID_INPUT.getMessage());
            }
        }
        while (!exit);
    }

    private int optionInput() {
        int menuOption;
        try {
            out.print("Choose an option: ");
            menuOption = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // option = -1 will be proceeded in default switch case
        }
        return menuOption;
    }
}