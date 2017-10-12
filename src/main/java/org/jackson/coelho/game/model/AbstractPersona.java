package org.jackson.coelho.game.model;

import org.jackson.coelho.game.enums.Color;
import org.jackson.coelho.game.enums.PersonaGenre;
import org.jackson.coelho.game.enums.TypeClass;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by jackson on 12/10/17.
 */
public class AbstractPersona {

    private String name;
    private PersonaGenre personaGenre;
    private Integer currentLevel;
    private Integer nextLevelXp;
    private Integer maxHealth;
    private Color eyeColor;
    private Color hairColor;
    private TypeClass typeClass;
    private Integer attackFactor;
    private Integer defenseFactor;
    private Position currentPosition;
    private Integer currentHealth;

    public AbstractPersona() {
        this.currentLevel = 1;
        this.nextLevelXp = 100;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PersonaGenre getPersonaGenre() {
        return personaGenre;
    }

    public void setPersonaGenre(PersonaGenre personaGenre) {
        this.personaGenre = personaGenre;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public TypeClass getTypeClass() {
        return typeClass;
    }

    public void setTypeClass(TypeClass typeClass) {
        this.typeClass = typeClass;
        int defense = ThreadLocalRandom.current().nextInt(typeClass.getMaxDefenseFactor() - 10, typeClass.getMaxDefenseFactor() + 1);
        int attack = ThreadLocalRandom.current().nextInt(typeClass.getMaxAttackFactor() - 10, typeClass.getMaxAttackFactor() + 1);
        int health = ThreadLocalRandom.current().nextInt(typeClass.getMinHealth(), typeClass.getMaxHealth() + 1);
        setDefenseFactor(defense);
        setAttackFactor(attack);
        setMaxHealth(health);
        setCurrentHealth(getMaxHealth());
    }

    public Color getHairColor() {
        return hairColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public Integer getAttackFactor() {
        return attackFactor;
    }

    public void setAttackFactor(Integer attackFactor) {
        this.attackFactor = attackFactor;
    }

    public Integer getDefenseFactor() {
        return defenseFactor;
    }

    public void setDefenseFactor(Integer defenseFactor) {
        this.defenseFactor = defenseFactor;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Integer getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(Integer currentHealth) {
        this.currentHealth = currentHealth;
    }

    public Integer getNextLevelXp() {
        return nextLevelXp;
    }

    public void setNextLevelXp(Integer nextLevelXp) {
        this.nextLevelXp = nextLevelXp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractPersona that = (AbstractPersona) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (personaGenre != that.personaGenre) return false;
        if (currentLevel != null ? !currentLevel.equals(that.currentLevel) : that.currentLevel != null) return false;
        if (nextLevelXp != null ? !nextLevelXp.equals(that.nextLevelXp) : that.nextLevelXp != null) return false;
        if (maxHealth != null ? !maxHealth.equals(that.maxHealth) : that.maxHealth != null) return false;
        if (eyeColor != that.eyeColor) return false;
        if (hairColor != that.hairColor) return false;
        if (typeClass != that.typeClass) return false;
        if (attackFactor != null ? !attackFactor.equals(that.attackFactor) : that.attackFactor != null) return false;
        if (defenseFactor != null ? !defenseFactor.equals(that.defenseFactor) : that.defenseFactor != null)
            return false;
        if (currentPosition != null ? !currentPosition.equals(that.currentPosition) : that.currentPosition != null)
            return false;
        return currentHealth != null ? currentHealth.equals(that.currentHealth) : that.currentHealth == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (personaGenre != null ? personaGenre.hashCode() : 0);
        result = 31 * result + (currentLevel != null ? currentLevel.hashCode() : 0);
        result = 31 * result + (nextLevelXp != null ? nextLevelXp.hashCode() : 0);
        result = 31 * result + (maxHealth != null ? maxHealth.hashCode() : 0);
        result = 31 * result + (eyeColor != null ? eyeColor.hashCode() : 0);
        result = 31 * result + (hairColor != null ? hairColor.hashCode() : 0);
        result = 31 * result + (typeClass != null ? typeClass.hashCode() : 0);
        result = 31 * result + (attackFactor != null ? attackFactor.hashCode() : 0);
        result = 31 * result + (defenseFactor != null ? defenseFactor.hashCode() : 0);
        result = 31 * result + (currentPosition != null ? currentPosition.hashCode() : 0);
        result = 31 * result + (currentHealth != null ? currentHealth.hashCode() : 0);
        return result;
    }
}
