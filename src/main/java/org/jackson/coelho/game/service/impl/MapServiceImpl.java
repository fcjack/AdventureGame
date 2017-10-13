package org.jackson.coelho.game.service.impl;

import org.jackson.coelho.game.enums.Direction;
import org.jackson.coelho.game.enums.TypeClass;
import org.jackson.coelho.game.model.Enemy;
import org.jackson.coelho.game.model.MapPoint;
import org.jackson.coelho.game.model.Position;
import org.jackson.coelho.game.service.MapService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by jackson on 12/10/17.
 */
@Service
public class MapServiceImpl implements MapService {

    @Value(value = "classpath:map.txt")
    private Resource mapFile;

    private Integer maxHorizontal;
    private Integer maxVertical;
    private Set<Enemy> enemies;
    private MapPoint[][] map;

    @Override
    public void buildMap() throws IOException {
        if (!isReady()) {
            processMapFile();
        }
    }

    private void processMapFile() throws IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(mapFile.getInputStream()))) {
            loadEnemies(new HashSet<>());
            List<String> lines = buffer.lines().collect(Collectors.toList());

            maxVertical = lines.get(0).length();
            maxHorizontal = Math.toIntExact(lines.size());
            map = new MapPoint[maxHorizontal][maxVertical];

            final int[] lineIndex = {0};
            lines.forEach(line -> {
                Random random = new Random();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == 'E') {
                        Position enemyPosition = new Position(lineIndex[0], i);
                        int classRandom = random.nextInt(4);
                        Enemy enemy = new Enemy(TypeClass.getValueByIndex(classRandom));
                        enemy.setCurrentPosition(enemyPosition);

                        map[lineIndex[0]][i] = new MapPoint(enemy);
                        enemies.add(enemy);
                    } else if (line.charAt(i) == 'R') {
                        MapPoint point = new MapPoint();
                        point.setPosition(new Position(lineIndex[0], i));
                        point.setRecover(true);
                        map[lineIndex[0]][i] = point;
                    } else {
                        MapPoint point = new MapPoint();
                        point.setPosition(new Position(lineIndex[0], i));
                        map[lineIndex[0]][i] = point;
                    }
                }
                lineIndex[0]++;
            });
        }
    }

    @Override
    public MapPoint movePersona(Position currentPosition, Direction direction, int steps) {
        switch (direction) {
            case UP:
                return moveUp(currentPosition, steps);
            case DOWN:
                return moveDown(currentPosition, steps);
            case LEFT:
                return moveLeft(currentPosition, steps);
            case RIGHT:
                return moveRight(currentPosition, steps);
        }
        throw new IllegalArgumentException();
    }

    private MapPoint moveDown(Position currentPosition, int steps) {
        if ((currentPosition.getHorizontal() + steps) > getMaxHorizontal()) {
            steps = getMaxHorizontal() - currentPosition.getHorizontal() - 1;
        }

        int nextPosition = currentPosition.getHorizontal();
        for (int i = 0; i < steps; i++) {
            nextPosition++;
            MapPoint mapPoint = map[nextPosition][currentPosition.getVertical()];
            if (mapPoint.hasEnemy()) {
                return mapPoint;
            }
        }

        return map[nextPosition][currentPosition.getVertical()];
    }

    private MapPoint moveLeft(Position currentPosition, int steps) {
        if ((currentPosition.getVertical() - steps) < 0) {
            steps = steps + (currentPosition.getVertical() - steps);
        }

        int nextPosition = currentPosition.getVertical();
        for (int i = 0; i < steps; i++) {
            nextPosition--;
            MapPoint mapPoint = map[currentPosition.getHorizontal()][nextPosition];
            if (mapPoint.hasEnemy()) {
                return mapPoint;
            }
        }

        return map[currentPosition.getHorizontal()][nextPosition];
    }

    private MapPoint moveRight(Position currentPosition, int steps) {
        if ((currentPosition.getVertical() + steps) > getMaxVertical()) {
            steps = getMaxVertical() - currentPosition.getVertical() - 1;
        }

        int nextPosition = currentPosition.getVertical();
        for (int i = 0; i < steps; i++) {
            nextPosition++;
            MapPoint mapPoint = map[currentPosition.getHorizontal()][nextPosition];
            if (mapPoint.hasEnemy() || mapPoint.isRecover()) {
                return mapPoint;
            }
        }

        return map[currentPosition.getHorizontal()][nextPosition];
    }

    private MapPoint moveUp(Position currentPosition, int steps) {
        if ((currentPosition.getHorizontal() - steps) < 0) {
            steps = steps + (currentPosition.getHorizontal() - steps);
        }

        int nextPosition = currentPosition.getHorizontal();
        for (int i = 0; i < steps; i++) {
            nextPosition--;
            MapPoint mapPoint = map[nextPosition][currentPosition.getVertical()];
            if (mapPoint.hasEnemy()) {
                return mapPoint;
            }
        }

        return map[nextPosition][currentPosition.getVertical()];
    }

    @Override
    public Integer getMaxHorizontal() {
        return maxHorizontal - 1;
    }

    @Override
    public Integer getMaxVertical() {
        return maxVertical - 1;
    }

    @Override
    public boolean hasEnemiesOnGame() {
        return !enemies.isEmpty();
    }

    @Override
    public void removeEnemy(MapPoint mapPoint) {
        int horizontal = mapPoint.getPosition().getHorizontal();
        int vertical = mapPoint.getPosition().getVertical();
        enemies.remove(mapPoint.getEnemy());
        mapPoint.setEnemy(null);
        map[horizontal][vertical] = mapPoint;
    }

    @Override
    public Set<Enemy> getEnemies() {
        return enemies;
    }

    @Override
    public MapPoint[][] getMap() {
        return map;
    }

    @Override
    public void loadMap(MapPoint[][] map) {
        this.map = map;
        this.maxVertical = map[0].length;
        this.maxHorizontal = map.length;
    }

    @Override
    public void loadEnemies(Set<Enemy> enemies) {
        this.enemies = enemies;
    }

    private boolean isReady() {
        return map != null && enemies != null;
    }
}
