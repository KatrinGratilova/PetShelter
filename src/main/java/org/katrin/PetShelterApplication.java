package org.katrin;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PetShelterApplication {
    private final PrintStream out;
    private final Scanner scanner;
    private final MenuOptionHandler optionHandler;

    public PetShelterApplication(){
        out = new PrintStream(System.out);
        scanner = new Scanner(System.in);
        optionHandler = new MenuOptionHandler();
    }

    public static void main(String[] args) {
        PetShelterApplication shelterApp = new PetShelterApplication();
        shelterApp.run();
    }

    public void run(){
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

    private int optionInput(){
        int menuOption = - 1;
        do {
            try {
                out.print("\nChoose an option: ");
                menuOption = scanner.nextInt();
            } catch (InputMismatchException e) {
                scanner.nextLine();
                out.println(Messages.INVALID_INPUT.getMessage());
            }
        } while (menuOption < 0);
        scanner.nextLine();
        return menuOption;
    }
}