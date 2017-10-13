package org.jackson.coelho.game.service.impl;

import org.jackson.coelho.game.enums.TypeClass;
import org.jackson.coelho.game.model.Enemy;
import org.jackson.coelho.game.model.Persona;
import org.jackson.coelho.game.service.FightService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by jackson on 12/10/17.
 */
public class FightServiceImplTest {


    private Persona persona;
    private Enemy enemy;

    @Before
    public void setup() {
        persona = new Persona();
        persona.setTypeClass(TypeClass.ASSASSIN);

        enemy = new Enemy(TypeClass.BARBARIAN);
    }

    @Test
    public void testFightWithPersonaWin() {
        FightService fightService = new FightServiceImpl();

        persona.setAttackFactor(50);
        enemy.setAttackFactor(10);

        boolean result = fightService.fight(persona, enemy);
        Assert.assertTrue(result);
    }

    @Test
    public void testFightWithPersonaLost() {
        FightService fightService = new FightServiceImpl();

        persona.setAttackFactor(20);
        persona.setCurrentHealth(70);
        persona.setDefenseFactor(10);
        enemy.setAttackFactor(50);
        enemy.setCurrentHealth(150);
        enemy.setDefenseFactor(50);

        boolean result = fightService.fight(persona, enemy);
        Assert.assertFalse(result);
    }

}