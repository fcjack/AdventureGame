package org.jackson.coelho.game.enums;

/**
 * Created by jackson on 12/10/17.
 */
public enum PersonaGenre {

    MALE('M'), FEMALE('F');

    private Character symbol;

    PersonaGenre(char symbol) {
        this.symbol = symbol;
    }

    public static PersonaGenre getGenreBySymbol(char symbol) {
        for (PersonaGenre personaGenre : values()) {
            if (personaGenre.getSymbol().equals(symbol)) {
                return personaGenre;
            }
        }

        throw new IllegalArgumentException("The value of the symbol is invalid");
    }

    public Character getSymbol() {
        return symbol;
    }
}
