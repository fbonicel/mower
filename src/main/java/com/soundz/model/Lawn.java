package com.soundz.model;

public final class Lawn {

    public final int width;
    public final int height;

    public Lawn(int width,int height){
        if (width != height)
            throw new IllegalArgumentException("Lawn must be a square.");

        if (width < 1)
            throw new IllegalArgumentException("Lawn size must be > 0.");

        this.width = width;
        this.height = height;
    }

}
