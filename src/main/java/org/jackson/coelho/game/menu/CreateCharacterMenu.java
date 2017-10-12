package org.jackson.coelho.game.menu;

import org.jackson.coelho.game.enums.Color;
import org.jackson.coelho.game.enums.PersonaGenre;
import org.jackson.coelho.game.enums.TypeClass;
import org.jackson.coelho.game.model.Persona;
import org.jackson.coelho.game.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

/**
 * Created by jackson on 12/10/17.
 */
@Component
public class CreateCharacterMenu extends AbstractMenu {

    private final ExploreMenu exploreMenu;

    @Autowired
    public CreateCharacterMenu(ExploreMenu exploreMenu) {
        this.exploreMenu = exploreMenu;
    }

    @Override
    public void init() {
        System.out.println("Let`s begin creating your persona! \\o/");
        createPersona();
    }

    private void createPersona() {
        Scanner scanner = new Scanner(System.in);
        Persona persona = new Persona();

        persona.setCurrentXp(0);
        persona.setCurrentHealth(100);
        persona.setMaxHealth(100);
        persona.setCurrentLevel(1);
        persona.setCurrentPosition(new Position(0, 0));
        getPersonaGenre(persona);
        getEyeColor(persona);
        getHairColor(persona);
        getTypeClass(persona);


        System.out.println("Finally what is your persona`s name?");
        persona.setName(scanner.next());

        System.out.println(persona);
        beginGame(persona);
    }

    private void beginGame(Persona persona) {
        System.out.println("Can we begin the game? (Y=Yes, N=Not)");
        String beginOption = getScanner().next();
        switch (beginOption.trim().toUpperCase()) {
            case "Y":
                exploreMenu.setCurrentPersona(persona);
                exploreMenu.init();
                break;
            case "N":
                System.out.println("Let` try make your persona again");
                createPersona();
                break;
            default:
                System.out.println("Invalid option, try again!");
                beginGame(persona);
        }
    }

    private void getTypeClass(Persona persona) {
        System.out.println("What is the class of the persona?");
        for (int i = 0; i < TypeClass.values().length; i++) {
            System.out.println(String.format("(%d) %s", (i + 1), TypeClass.values()[i]));
        }

        try {
            int personaClass = getScanner().nextInt();
            persona.setTypeClass(TypeClass.getValueByIndex(--personaClass));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid option for type class!!");
            getTypeClass(persona);
        }
    }

    private void getEyeColor(Persona persona) {
        System.out.println("What is the eyes color?");
        for (int i = 0; i < Color.values().length; i++) {
            System.out.println(String.format("(%d) %s", (i + 1), Color.values()[i]));
        }

        try {
            int eyeColor = getScanner().nextInt();
            persona.setEyeColor(Color.getValueByIndex(--eyeColor));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid option for eye color!!");
            getEyeColor(persona);
        }
    }

    private void getHairColor(Persona persona) {
        System.out.println("What is the hair color for persona?");
        for (int i = 0; i < Color.values().length; i++) {
            System.out.println(String.format("(%d) %s", (i + 1), Color.values()[i]));
        }

        try {
            int hairColor = getScanner().nextInt();
            persona.setHairColor(Color.getValueByIndex(--hairColor));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid option for hair color!!");
            getHairColor(persona);
        }
    }


    private void getPersonaGenre(Persona persona) {
        System.out.println("What is the gender (M) Male or (F) Female?");
        try {
            String genreOption = getScanner().next();
            persona.setPersonaGenre(PersonaGenre.getGenreBySymbol(genreOption.trim().charAt(0)));
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid option for genre");
            getPersonaGenre(persona);
        }
    }

}
