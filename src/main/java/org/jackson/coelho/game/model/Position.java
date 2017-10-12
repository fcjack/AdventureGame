package org.jackson.coelho.game.model;

/**
 * Created by jackson on 12/10/17.
 */
public class Position {

    private Integer horizontal;
    private Integer vertical;

    public Position(int horizontal, int vertical) {
        this.horizontal = horizontal;
        this.vertical = vertical;
    }

    public Integer getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(Integer horizontal) {
        this.horizontal = horizontal;
    }

    public Integer getVertical() {
        return vertical;
    }

    public void setVertical(Integer vertical) {
        this.vertical = vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (horizontal != null ? !horizontal.equals(position.horizontal) : position.horizontal != null) return false;
        return vertical != null ? vertical.equals(position.vertical) : position.vertical == null;
    }

    @Override
    public int hashCode() {
        int result = horizontal != null ? horizontal.hashCode() : 0;
        result = 31 * result + (vertical != null ? vertical.hashCode() : 0);
        return result;
    }
}
