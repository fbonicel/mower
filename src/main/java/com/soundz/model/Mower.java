package com.soundz.model;

import java.util.List;
import java.util.Optional;

public final class Mower implements Mobile {

    private Position position;
    private Orientation orientation;

    public Mower(Position position, Orientation orientation) {
        this.position = new Position(position.getX(), position.getY());
        this.orientation = Orientation.valueOf(orientation.name());
    }

    @Override
    public Mower turnLeft() {
        Mower newMower;
        switch (orientation) {
            case N:
                newMower = new Mower(position, Orientation.W);
                break;
            case S:
                newMower = new Mower(position, Orientation.E);
                break;
            case E:
                newMower = new Mower(position, Orientation.N);
                break;
            case W:
                newMower = new Mower(position, Orientation.S);
                break;
            default:
                newMower = this;
        }
        return newMower;
    }

    @Override
    public Mower turnRight() {
        Mower newMower;
        switch (orientation) {
            case N:
                newMower = new Mower(position, Orientation.E);
                break;
            case S:
                newMower = new Mower(position, Orientation.W);
                break;
            case E:
                newMower = new Mower(position, Orientation.S);
                break;
            case W:
                newMower = new Mower(position, Orientation.N);
                break;
            default:
                newMower = this;
        }
        return newMower;
    }

    @Override
    public Mower moveForward() {
        Mower newMower;
        switch (orientation) {
            case N:
                newMower = new Mower(new Position(position.getX(), position.getY() + 1), orientation);
                break;
            case S:
                newMower = new Mower(new Position(position.getX(), position.getY() - 1), orientation);
                break;
            case E:
                newMower = new Mower(new Position(position.getX() + 1, position.getY()), orientation);
                break;
            case W:
                newMower = new Mower(new Position(position.getX() - 1, position.getY()), orientation);
                break;
            default:
                newMower = this;
        }
        return newMower;
    }

    @Override
    public Mower move(Character instruction) {
        Mower newMower;
        switch (instruction) {
            case 'L':
                newMower = turnLeft();
                break;
            case 'R':
                newMower = turnRight();
                break;
            case 'F':
                newMower = moveForward();
                break;
            default:
                System.out.println("Unkwnown instruction. I don't move!");
                newMower = this;
        }

        return newMower;
    }

    public Position getPosition(){
        return position;
    }


    public Optional<Mower> validatePosition(Lawn lawn, List<Position> occupiedPositions) {
        return (insideLawn(lawn) && !occupiedPositions.contains(position)) ? Optional.of(this) : Optional.empty();
    }

    private Boolean insideLawn(Lawn lawn) {
        return position.getX() >= 0 && position.getY() >= 0 && position.getX() <= lawn.width && position.getY() <= lawn.height;
    }



    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Mower)) {
            return false;
        }
        Mower m = (Mower) o;
        return position.equals(m.position)
                && orientation.equals(m.orientation);
    }

    @Override
    public int hashCode() {
        //TODO: KNOW HOW TO EXPLAIN
        int result = 17;

        result = 31 * result + orientation.name().hashCode();
        result = 31 * result + position.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s", position, orientation);
    }
}
