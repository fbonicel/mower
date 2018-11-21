package com.soundz.model;

public final class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.y = y;
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Position)) {
            return false;
        }

        Position p = (Position) o;

        return x == p.getX() && y == p.getY();
    }

    @Override
    public String toString() {
        return String.format("%s %s", x, y);
    }
}
