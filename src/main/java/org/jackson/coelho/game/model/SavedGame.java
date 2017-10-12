package org.jackson.coelho.game.model;

import java.io.Serializable;
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
}
