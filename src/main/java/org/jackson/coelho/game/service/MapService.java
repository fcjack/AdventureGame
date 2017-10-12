package org.jackson.coelho.game.service;

import org.jackson.coelho.game.enums.Direction;
import org.jackson.coelho.game.model.Enemy;
import org.jackson.coelho.game.model.MapPoint;
import org.jackson.coelho.game.model.Position;

import java.io.IOException;
import java.util.Set;

/**
 * Created by jackson on 12/10/17.
 */
public interface MapService {

    void buildMap() throws IOException;

    MapPoint movePersona(Position currentPosition, Direction direction, int steps);

    Integer getMaxHorizontal();

    Integer getMaxVertical();

    boolean hasEnemiesOnGame();

    void removeEnemy(MapPoint mapPoint);

    Set<Enemy> getEnemies();

    MapPoint[][] getMap();

    void loadMap(MapPoint[][] map);

    void loadEnemies(Set<Enemy> enemies);
}
