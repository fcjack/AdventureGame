package org.jackson.coelho.game.enums;

/**
 * Created by jackson on 12/10/17.
 */
public enum TypeClass {
    BARBARIAN(70, 50, 170, 200), ASSASSIN(35, 60, 130, 150), MYSTIC(65, 30, 100, 120), TECH(53, 47, 150, 170);

    private Integer maxAttackFactor;
    private Integer maxDefenseFactor;
    private Integer maxHealth;
    private Integer minHealth;

    TypeClass(int maxAttackFactor, int maxDefenseFactor, int minHealth, int maxHealth) {
        this.maxAttackFactor = maxAttackFactor;
        this.maxDefenseFactor = maxDefenseFactor;
        this.maxHealth = maxHealth;
        this.minHealth = minHealth;
    }

    public static TypeClass getValueByIndex(int personaClass) {
        if (personaClass < 0 || personaClass > TypeClass.values().length) {
            throw new IllegalArgumentException();
        }

        return TypeClass.values()[personaClass];
    }

    public Integer getMaxAttackFactor() {
        return maxAttackFactor;
    }

    public Integer getMaxDefenseFactor() {
        return maxDefenseFactor;
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public Integer getMinHealth() {
        return minHealth;
    }
}
