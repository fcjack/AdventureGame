package org.jackson.coelho.game.model;

/**
 * Created by jackson on 12/10/17.
 */
public class MapPoint {

    private Position position;
    private Enemy enemy;
    private boolean recover;

    public MapPoint() {
    }

    public MapPoint(Enemy enemy) {
        this.position = enemy.getCurrentPosition();
        this.enemy = enemy;
        this.recover = false;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public boolean hasEnemy() {
        return getEnemy() != null;
    }

    public boolean isRecover() {
        return recover;
    }

    public void setRecover(boolean recover) {
        this.recover = recover;
    }
}
