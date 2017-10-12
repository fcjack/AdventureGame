package org.jackson.coelho.game.model;

/**
 * Created by jackson on 12/10/17.
 */
public class Persona extends AbstractPersona {

    private int currentXp;

    public Persona() {
        super();
    }

    public int getCurrentXp() {
        return currentXp;
    }

    public void setCurrentXp(int currentXp) {
        this.currentXp = currentXp;
    }

    @Override
    public String toString() {
        return String.format("I am %s, my class is of type %s, as you can see my eyes are %s and hair is %s. Let`s begin our adventure.", getName(), getTypeClass(), getEyeColor(), getHairColor());
    }

    public void recoverHP() {
        setCurrentHealth(getMaxHealth());
    }

    public boolean isAlive() {
        return getCurrentHealth() > 0;
    }

    public void upgradeLevel() {
        setCurrentLevel(getCurrentLevel() + 1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Persona)) return false;
        if (!super.equals(o)) return false;

        Persona persona = (Persona) o;

        return currentXp == persona.currentXp;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + currentXp;
        return result;
    }
}
