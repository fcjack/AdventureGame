package org.jackson.coelho.game.service.impl;

import org.jackson.coelho.game.model.Enemy;
import org.jackson.coelho.game.model.Persona;
import org.jackson.coelho.game.service.FightService;
import org.springframework.stereotype.Service;

/**
 * Created by jackson on 12/10/17.
 */
@Service
public class FightServiceImpl implements FightService {

    @Override
    public boolean fight(Persona currentPersona, Enemy enemy) {
        while (currentPersona.getCurrentHealth() > 0 && enemy.getCurrentHealth() > 0) {
            int damage = enemy.getAttackFactor() - currentPersona.getDefenseFactor();
            int enemyDamage = currentPersona.getAttackFactor() - enemy.getDefenseFactor();
            if (damage < 0) damage = 0;
            if (enemyDamage < 0) enemyDamage = 0;

            int personaHealth = currentPersona.getCurrentHealth() - damage;
            int enemyHealth = enemy.getCurrentHealth() - enemyDamage;

            currentPersona.setCurrentHealth(personaHealth);
            enemy.setCurrentHealth(enemyHealth);
        }

        return currentPersona.getCurrentHealth() > 0;
    }
}
