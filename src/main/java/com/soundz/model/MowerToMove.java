package com.soundz.model;

import java.util.stream.Stream;

public final class MowerToMove {

    public final Mower mower;
    public final String instructions;


    public MowerToMove(Mower mower, String instructions) {
        this.mower = new Mower(mower.position,mower.orientation);
        this.instructions = instructions;
    }

    // Convert IntStream to Stream<Character>
    public Stream<Character> getInstructionsStream() {
        return instructions.chars().mapToObj(i -> (char) i);
    }

}
