package org.example.snakeandladdergame.model;

public class Cell {
    private int position;
    private Jump jump;

    public Cell(int position) {
        this.position = position;
    }

    public int getPosition() { return position; }

    public Jump getJump() { return jump; }
    public void setJump(Jump jump) { this.jump = jump; }
}
