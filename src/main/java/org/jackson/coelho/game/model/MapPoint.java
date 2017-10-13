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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MapPoint)) return false;

        MapPoint mapPoint = (MapPoint) o;

        if (recover != mapPoint.recover) return false;
        if (position != null ? !position.equals(mapPoint.position) : mapPoint.position != null) return false;
        return enemy != null ? enemy.equals(mapPoint.enemy) : mapPoint.enemy == null;
    }

    @Override
    public int hashCode() {
        int result = position != null ? position.hashCode() : 0;
        result = 31 * result + (enemy != null ? enemy.hashCode() : 0);
        result = 31 * result + (recover ? 1 : 0);
        return result;
    }
}
