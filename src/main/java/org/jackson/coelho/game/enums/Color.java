package org.jackson.coelho.game.enums;

/**
 * Created by jackson on 12/10/17.
 */
public enum Color {
    BLACK, WHITE, RED, YELLOW, BLUE;

    public static Color getValueByIndex(int color) {
        if (color < 0 || color > Color.values().length) {
            throw new IllegalArgumentException();
        }

        return Color.values()[color];
    }
}
