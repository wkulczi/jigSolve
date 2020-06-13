package com.example.jigsolveclient.data;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Puzzle implements Serializable {

    Bitmap puzzle;

    public Bitmap getPuzzle() {
        return puzzle;
    }

    public void setPuzzle(Bitmap puzzle) {
        this.puzzle = puzzle;
    }
}
