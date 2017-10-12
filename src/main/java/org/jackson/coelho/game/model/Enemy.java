package org.jackson.coelho.game.model;

import org.jackson.coelho.game.enums.TypeClass;

import java.util.Objects;
import java.util.Random;

/**
 * Created by jackson on 12/10/17.
 */
public class Enemy extends AbstractPersona {

    private Integer xpRewards;

    public Enemy(TypeClass typeClass) {
        setTypeClass(typeClass);
        Random random = new Random();
        setXpRewards(random.nextInt((getAttackFactor() + getDefenseFactor())));
    }

    public Integer getXpRewards() {
        return xpRewards;
    }

    public void setXpRewards(Integer xpRewards) {
        this.xpRewards = xpRewards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enemy)) return false;
        if (!super.equals(o)) return false;

        Enemy enemy = (Enemy) o;

        return xpRewards != null ? xpRewards.equals(enemy.xpRewards) : enemy.xpRewards == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (xpRewards != null ? xpRewards.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Enemy for class %s with the attack %d and defense %d, at least my HP is %d", getTypeClass(), getAttackFactor(), getDefenseFactor(), getCurrentHealth());
    }
}
