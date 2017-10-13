package org.jackson.coelho.game.service.impl;

import org.jackson.coelho.game.enums.Direction;
import org.jackson.coelho.game.enums.TypeClass;
import org.jackson.coelho.game.menu.FightMenu;
import org.jackson.coelho.game.model.Enemy;
import org.jackson.coelho.game.model.MapPoint;
import org.jackson.coelho.game.model.Persona;
import org.jackson.coelho.game.model.Position;
import org.jackson.coelho.game.service.MapService;
import org.jackson.coelho.game.service.PersonaService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * Created by jackson on 12/10/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ExploreServiceImplTest {

    @Mock
    public MapService mapService;

    @Mock
    public FightMenu fightMenu;

    @Mock
    public PersonaService personaService;

    @InjectMocks
    private ExploreServiceImpl exploreService;

    private MapPoint mapPoint;

    @Before
    public void setup() {
        mapPoint = new MapPoint();
        mapPoint.setPosition(new Position(0, 3));
        Enemy enemy = new Enemy(TypeClass.BARBARIAN);
        mapPoint.setEnemy(enemy);
    }

    @Test
    public void testExploreAndWinFight() {
        Persona persona = new Persona();
        persona.setTypeClass(TypeClass.BARBARIAN);
        persona.setCurrentXp(10);

        Mockito.when(mapService.movePersona(Mockito.any(), Mockito.any(), Mockito.anyInt())).thenReturn(mapPoint);
        Mockito.when(fightMenu.processFightAction()).thenReturn(true);
        exploreService.explore(persona, 5, Direction.RIGHT);
        Assert.assertEquals(true, persona.isAlive());
    }

    @Test
    public void testExploreAndLostFight() {
        Persona persona = new Persona();
        persona.setTypeClass(TypeClass.BARBARIAN);

        Mockito.when(mapService.movePersona(Mockito.any(), Mockito.any(), Mockito.anyInt())).thenReturn(mapPoint);
        Mockito.when(fightMenu.processFightAction()).thenReturn(false);
        persona.setCurrentHealth(0);
        exploreService.explore(persona, 5, Direction.RIGHT);

        Assert.assertEquals(false, persona.isAlive());
    }

    @Test
    public void testExploreAndRecoverPoint() {
        Persona persona = new Persona();
        persona.setTypeClass(TypeClass.BARBARIAN);
        persona.setCurrentHealth(10);
        mapPoint.setEnemy(null);
        mapPoint.setRecover(true);

        Mockito.when(mapService.movePersona(Mockito.any(), Mockito.any(), Mockito.anyInt())).thenReturn(mapPoint);
        exploreService.explore(persona, 5, Direction.RIGHT);

        Assert.assertEquals(persona.getMaxHealth(), persona.getCurrentHealth());
    }
}