package org.jackson.coelho.game.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by jackson on 12/10/17.
 */
public class SavedGame implements Serializable {

    private String name;
    private Persona persona;
    private Set<Enemy> enemies;
    private MapPoint[][] map;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Set<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(Set<Enemy> enemies) {
        this.enemies = enemies;
    }

    public MapPoint[][] getMap() {
        return map;
    }

    public void setMap(MapPoint[][] map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SavedGame)) return false;

        SavedGame savedGame = (SavedGame) o;

        if (name != null ? !name.equals(savedGame.name) : savedGame.name != null) return false;
        if (persona != null ? !persona.equals(savedGame.persona) : savedGame.persona != null) return false;
        if (enemies != null ? !enemies.equals(savedGame.enemies) : savedGame.enemies != null) return false;
        return Arrays.deepEquals(map, savedGame.map);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (persona != null ? persona.hashCode() : 0);
        result = 31 * result + (enemies != null ? enemies.hashCode() : 0);
        result = 31 * result + Arrays.deepHashCode(map);
        return result;
    }
}
