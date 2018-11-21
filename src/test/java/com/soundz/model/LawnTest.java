package com.soundz.model;

import org.junit.Test;

/**
 * Unit test for Lawn.
 */
public class LawnTest {

    @Test(expected = IllegalArgumentException.class)
    public void lawn_with_different_width_and_height_should_not_be_valid() {
        new Lawn(1, 2);
    }

}