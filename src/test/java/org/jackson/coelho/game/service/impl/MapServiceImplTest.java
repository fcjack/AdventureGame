package org.jackson.coelho.game.service.impl;

import org.jackson.coelho.game.enums.Direction;
import org.jackson.coelho.game.enums.TypeClass;
import org.jackson.coelho.game.model.Enemy;
import org.jackson.coelho.game.model.MapPoint;
import org.jackson.coelho.game.model.Persona;
import org.jackson.coelho.game.model.Position;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jackson on 12/10/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MapServiceImplTest {

    @Mock
    private Resource mapFile;

    @InjectMocks
    private MapServiceImpl mapService;


    @Test
    public void buildMap() throws Exception {

        Resource fileResource = new FileSystemResource("src/test/resources/map.txt");
        Mockito.when(mapFile.getFile()).thenReturn(fileResource.getFile());
        mapService.buildMap();

        Assert.assertEquals(9, mapService.getMaxHorizontal().intValue());
        Assert.assertEquals(18, mapService.getMaxVertical().intValue());
        Assert.assertEquals(52, mapService.getEnemies().size());
    }

    @Test
    public void movePersona() throws Exception {
        Persona persona = new Persona();
        persona.setCurrentPosition(new Position(0, 0));

        MapPoint[][] map = new MapPoint[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = new MapPoint();
            }
        }

        MapPoint enemyPoint = new MapPoint();
        enemyPoint.setEnemy(new Enemy(TypeClass.MYSTIC));
        enemyPoint.setPosition(new Position(0, 3));
        map[0][3] = enemyPoint;
        mapService.loadMap(map);

        MapPoint mapPoint = mapService.movePersona(new Position(0, 0), Direction.RIGHT, 3);
        Assert.assertEquals(map[0][3], mapPoint);

        map[0][3] = new MapPoint();
        MapPoint emptyPoint = mapService.movePersona(new Position(0, 0), Direction.RIGHT, 12);
        Assert.assertEquals(map[0][9], emptyPoint);

        map[3][0] = enemyPoint;
        MapPoint enemyPoint2 = mapService.movePersona(new Position(0, 0), Direction.DOWN, 3);
        Assert.assertEquals(map[3][0], enemyPoint2);

        map[3][0] = new MapPoint();
        MapPoint emptyPoint2 = mapService.movePersona(new Position(0, 0), Direction.DOWN, 12);
        Assert.assertEquals(map[9][0], emptyPoint2);


        map[1][0] = enemyPoint;
        MapPoint enemyPoint3 = mapService.movePersona(new Position(2, 0), Direction.UP, 1);
        Assert.assertEquals(map[1][0], enemyPoint3);

        map[1][0] = new MapPoint();
        MapPoint emptyPoint3 = mapService.movePersona(new Position(7, 0), Direction.UP, 10);
        Assert.assertEquals(map[0][0], emptyPoint3);

        map[0][1] = enemyPoint;
        MapPoint enemyPoint4 = mapService.movePersona(new Position(0, 4), Direction.LEFT, 4);
        Assert.assertEquals(map[0][1], enemyPoint4);

        map[0][1] = new MapPoint();
        MapPoint emptyPoint4 = mapService.movePersona(new Position(0, 8), Direction.LEFT, 12);
        Assert.assertEquals(map[0][0], emptyPoint4);
    }

    @Test
    public void getMaxHorizontal() throws Exception {
        MapPoint[][] map = new MapPoint[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = new MapPoint();
            }
        }

        mapService.loadMap(map);

        Assert.assertEquals(9, mapService.getMaxHorizontal().intValue());
    }

    @Test
    public void getMaxVertical() throws Exception {
        MapPoint[][] map = new MapPoint[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = new MapPoint();
            }
        }

        mapService.loadMap(map);

        Assert.assertEquals(9, mapService.getMaxVertical().intValue());
    }

    @Test
    public void hasEnemiesOnGame() throws Exception {
        Set<Enemy> enemies = new HashSet<>();
        enemies.add(new Enemy(TypeClass.MYSTIC));
        mapService.loadEnemies(enemies);

        Assert.assertTrue(mapService.hasEnemiesOnGame());
    }

    @Test
    public void removeEnemy() throws Exception {
        MapPoint[][] map = new MapPoint[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = new MapPoint();
            }
        }

        mapService.loadMap(map);

        Set<Enemy> enemies = new HashSet<>();
        Enemy enemy = new Enemy(TypeClass.MYSTIC);
        enemy.setCurrentPosition(new Position(4, 3));
        enemies.add(enemy);
        mapService.loadEnemies(enemies);

        MapPoint mapPoint = new MapPoint();
        mapPoint.setEnemy(enemy);
        mapPoint.setPosition(new Position(4, 3));

        mapService.removeEnemy(mapPoint);

        Assert.assertFalse(mapService.hasEnemiesOnGame());
    }

}