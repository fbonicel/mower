package com.soundz.model;

public final class Position {

    public final int x;
    public final int y;

    public Position(int x, int y) {
        this.y = y;
        this.x = x;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result = 31 * result + (x ^ (x >>> 32));
        result = 31 * result + (y ^ (y >>> 32));

        return result;
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

        return x == p.x && y == p.y;
    }

    @Override
    public String toString() {
        return String.format("%s %s", x, y);
    }
}
