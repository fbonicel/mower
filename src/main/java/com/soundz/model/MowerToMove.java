package com.soundz.model;

import java.util.stream.Stream;

public class MowerToMove {

    private Mower mower;
    private String instructions;

    public MowerToMove(Mower mower, String instructions) {
        this.mower = mower;
        this.instructions = instructions;
    }

    public Mower getMower() {
        return mower;
    }

    // Convert IntStream to Stream<Character>
    public Stream<Character> getInstructionsStream() {
        return instructions.chars().mapToObj(i -> (char) i);
    }

}
