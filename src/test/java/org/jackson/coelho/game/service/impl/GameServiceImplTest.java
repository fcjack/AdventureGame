package org.jackson.coelho.game.service.impl;

import org.jackson.coelho.game.enums.TypeClass;
import org.jackson.coelho.game.model.*;
import org.jackson.coelho.game.service.GameService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by jackson on 12/10/17.
 */
public class GameServiceImplTest {

    private SavedGame savedGame;

    @Before
    public void setup() {
        MapPoint[][] map = new MapPoint[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = new MapPoint();
            }
        }

        Set<Enemy> enemies = new HashSet<>();
        Enemy enemy = new Enemy(TypeClass.MYSTIC);
        enemy.setCurrentPosition(new Position(4, 3));
        enemies.add(enemy);

        Persona persona = new Persona();
        persona.setTypeClass(TypeClass.MYSTIC);
        persona.setName("test_save");

        savedGame = new SavedGame();
        savedGame.setPersona(persona);
        savedGame.setMap(map);
        savedGame.setEnemies(enemies);
        savedGame.setName("test_save_game");
    }

    @Test
    public void saveGame() throws Exception {
        GameService gameService = new GameServiceImpl();
        gameService.saveGame(savedGame);

        String userHomeFolder = System.getProperty("user.home");
        StringBuilder saveFolder = new StringBuilder(userHomeFolder).append(File.separator).append("savedGames").append(File.separator);
        saveFolder.append("test_save_game.json");
        Path path = Paths.get(saveFolder.toString());
        Assert.assertTrue(Files.exists(path));
    }

    @Test
    public void loadGames() throws Exception {
        GameService gameService = new GameServiceImpl();
        gameService.saveGame(savedGame);
        List<SavedGame> savedGames = gameService.loadGames();
        Assert.assertTrue(savedGames.contains(savedGame));
    }


    @After
    public void removeTestFiles() throws IOException {
        String userHomeFolder = System.getProperty("user.home");
        StringBuilder saveFolder = new StringBuilder(userHomeFolder).append(File.separator).append("savedGames").append(File.separator);

        Path path = Paths.get(saveFolder.toString());
        Files.list(path).forEach(file -> {
            String fileName = file.getFileName().toString();
            fileName = fileName.split("\\.")[0];

            if (fileName.contains("test")) {
                try {
                    Files.delete(file);
                } catch (IOException e) {
                }
            }
        });
    }

}