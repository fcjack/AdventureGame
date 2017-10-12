package org.jackson.coelho.game.enums;

/**
 * Created by jackson on 12/10/17.
 */
public enum Direction {

    LEFT, RIGHT, UP, DOWN;

    public static Direction getValueByIndex(int index) {
        return values()[index];
    }
}
