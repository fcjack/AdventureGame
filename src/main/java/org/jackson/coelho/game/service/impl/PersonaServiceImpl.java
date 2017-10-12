package org.jackson.coelho.game.service.impl;

import org.jackson.coelho.game.enums.TypeClass;
import org.jackson.coelho.game.model.Enemy;
import org.jackson.coelho.game.model.Persona;
import org.jackson.coelho.game.service.PersonaService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by jackson on 12/10/17.
 */
@Service
public class PersonaServiceImpl implements PersonaService {


    @Override
    public void improvePersona(Persona currentPersona, Enemy enemy) {
        int xp = enemy.getXpRewards();
        int currentXp = currentPersona.getCurrentXp();
        int nextLevelXp = currentPersona.getNextLevelXp();
        if ((currentXp + xp) > nextLevelXp) {
            currentPersona.upgradeLevel();
            int differenceXp = (currentXp + xp) - nextLevelXp;
            calculateAttributesValues(currentPersona, differenceXp);

        } else {
            currentPersona.setCurrentXp(currentXp + xp);
        }
    }

    private void calculateAttributesValues(Persona currentPersona, int differenceXp) {
        currentPersona.setNextLevelXp(currentPersona.getCurrentLevel() * 100);
        currentPersona.setCurrentXp(differenceXp);

        TypeClass typeClass = currentPersona.getTypeClass();

        int defense = ThreadLocalRandom.current().nextInt(typeClass.getMaxDefenseFactor() / currentPersona.getCurrentLevel(), typeClass.getMaxDefenseFactor() + 1);
        int attack = ThreadLocalRandom.current().nextInt(typeClass.getMaxAttackFactor() / currentPersona.getCurrentLevel(), typeClass.getMaxAttackFactor() + 1);
        int health = ThreadLocalRandom.current().nextInt(typeClass.getMinHealth() / currentPersona.getCurrentLevel(), typeClass.getMaxHealth() + 1);

        defense = Math.abs(currentPersona.getDefenseFactor() - defense);
        attack = Math.abs(currentPersona.getAttackFactor() - attack);
        health = Math.abs(currentPersona.getMaxHealth() - health);

        currentPersona.setDefenseFactor(currentPersona.getDefenseFactor() + defense);
        currentPersona.setAttackFactor(currentPersona.getAttackFactor() + attack);
        currentPersona.setMaxHealth(currentPersona.getMaxHealth() + health);
        currentPersona.setCurrentHealth(currentPersona.getMaxHealth() + health);
    }
}
