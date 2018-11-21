package com.soundz.model;

public interface Mobile {
    Mobile turnLeft();

    Mobile turnRight();

    Mobile moveForward();

    Mobile move(Character instruction);
}
