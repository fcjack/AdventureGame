package org.jackson.coelho.game.service.impl;

import org.jackson.coelho.game.enums.TypeClass;
import org.jackson.coelho.game.model.Enemy;
import org.jackson.coelho.game.model.Persona;
import org.jackson.coelho.game.service.PersonaService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jackson on 12/10/17.
 */
public class PersonaServiceImplTest {

    @Test
    public void testImprovePersonaWithoutUpgradeLevel() {
        PersonaService personaService = new PersonaServiceImpl();
        Persona persona = new Persona();
        persona.setTypeClass(TypeClass.BARBARIAN);
        persona.setCurrentXp(20);

        Enemy enemy = new Enemy(TypeClass.MYSTIC);
        enemy.setXpRewards(15);
        personaService.improvePersona(persona, enemy);

        Assert.assertEquals(35, persona.getCurrentXp());
    }

    @Test
    public void testImprovePersonaAndUpgradeLevel() {
        PersonaService personaService = new PersonaServiceImpl();
        Persona persona = new Persona();
        persona.setTypeClass(TypeClass.BARBARIAN);
        persona.setCurrentXp(80);
        Integer attack = persona.getAttackFactor();
        Integer defense = persona.getDefenseFactor();
        Integer health = persona.getMaxHealth();
        Integer currentLevel = persona.getCurrentLevel();

        Enemy enemy = new Enemy(TypeClass.MYSTIC);
        enemy.setXpRewards(35);
        personaService.improvePersona(persona, enemy);

        Assert.assertTrue(persona.getAttackFactor() > attack);
        Assert.assertTrue(persona.getDefenseFactor() > defense);
        Assert.assertTrue(persona.getMaxHealth() > health);
        Assert.assertTrue(persona.getCurrentLevel() > currentLevel);
    }
}