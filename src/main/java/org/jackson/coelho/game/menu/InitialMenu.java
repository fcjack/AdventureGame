package org.jackson.coelho.game.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;

/**
 * The initial menu for the beginning of the game
 * <p>
 * Created by jackson on 12/10/17.
 */
@Component
public class InitialMenu extends AbstractMenu {

    private final CreateCharacterMenu createCharacterMenu;
    private final LoadMenu loadMenu;

    @Autowired
    public InitialMenu(CreateCharacterMenu createCharacterMenu, LoadMenu loadMenu) {
        this.createCharacterMenu = createCharacterMenu;
        this.loadMenu = loadMenu;
    }

    @Override
    public void init() {
        System.out.println("WELCOME TO FANTASY GAME BETA VERSION");
        System.out.println("Type the number of the option that you prefer:");

        buildMenu();
        readOptions();
    }

    private void buildMenu() {
        System.out.println("(1) Start new game");
        System.out.println("(2) Load game");
    }

    private void readOptions() {
        int option;
        try {
            option = getScanner().nextInt();
            switch (option) {
                case 1:
                    createCharacterMenu.init();
                    break;
                case 2:
                    loadMenu.init();
                    break;
                default:
                    System.out.println("This option is invalid, please select the right one that you prefer.");
                    buildMenu();
                    readOptions();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid option, input a number relative to option");
            buildMenu();
            readOptions();
        }
    }
}
