package com.soundz.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for Mower.
 */
public class MowerTest {

    private Mower mower;
    private Position position;
    private Lawn lawn;

    @Before
    public void setup() {
        position = new Position(1, 1);
        mower = new Mower(position, Orientation.N);
        lawn = new Lawn(2,2);
    }

    @Test
    public void mower_should_turn_right() {
        assertEquals(mower.turnRight(), new Mower(position, Orientation.E));
    }

    @Test
    public void mower_should_turn_left() {
        assertEquals(mower.turnLeft(), new Mower(position, Orientation.W));
    }

    @Test
    public void mower_should_move_forward() {
        assertEquals(mower.moveForward(), new Mower(new Position(1,2), Orientation.N));
    }

    @Test
    public void mower_with_R_instruction_should_turn_right() {
        assertEquals(mower.move('R'), new Mower(position, Orientation.E));
    }

    @Test
    public void mower_with_L_instruction_should_turn_left() {
        assertEquals(mower.move('L'), new Mower(position, Orientation.W));
    }

    @Test
    public void mower_with_F_instruction_should_move_forward() {
        assertEquals(mower.move('F'), new Mower(new Position(1,2), Orientation.N));
    }

    @Test
    public void mower_with_X_instruction_should_not_move() {
        assertEquals(mower.move('X'), mower);
    }

    @Test
    public void mower_should_be_valid(){
        assertEquals(mower.validatePosition(lawn, Collections.EMPTY_LIST), Optional.of(mower));
    }

    @Test
    public void mower_with_negative_x_position_should_not_be_valid(){
        // GIVEN
        position = new Position(-1,1);
        Mower invalidMower = new Mower(position,Orientation.N);
        // WHEN
        Optional<Mower> result = invalidMower.validatePosition(lawn, Collections.EMPTY_LIST);
        // THEN
        assertEquals(result, Optional.empty());
    }

    @Test
    public void mower_with_negative_y_position_should_not_be_valid(){
        // GIVEN
        position = new Position(1,-1);
        Mower invalidMower = new Mower(position,Orientation.N);
        // WHEN
        Optional<Mower> result = invalidMower.validatePosition(lawn, Collections.EMPTY_LIST);
        // THEN
        assertEquals(result, Optional.empty());
    }

    @Test
    public void mower_with_outside_lawn_x_position_should_not_be_valid(){
        // GIVEN
        position = new Position(3,1);
        Mower invalidMower = new Mower(position,Orientation.N);
        // WHEN
        Optional<Mower> result = invalidMower.validatePosition(lawn, Collections.EMPTY_LIST);
        // THEN
        assertEquals(result, Optional.empty());
    }

    @Test
    public void mower_with_outside_lawn_y_position_should_not_be_valid(){
        // GIVEN
        position = new Position(3,4);
        Mower invalidMower = new Mower(position,Orientation.N);
        // WHEN
        Optional<Mower> result = invalidMower.validatePosition(lawn, Collections.EMPTY_LIST);
        // THEN
        assertEquals(result, Optional.empty());
    }
}
