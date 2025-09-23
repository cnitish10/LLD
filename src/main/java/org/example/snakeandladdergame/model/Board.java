package org.example.snakeandladdergame.model;

public class Board {
    private int rows;
    private int cols;
    private Cell[] cells;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        int totalCells = rows * cols;
        this.cells = new Cell[totalCells + 1]; // 1-based indexing
        for (int i = 1; i <= totalCells; i++) {
            cells[i] = new Cell(i);
        }
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public int getTotalCells() { return rows * cols; }

    public Cell getCell(int position) { return cells[position]; }

    public void addJump(Jump jump) {
        if (jump.getStart() > getTotalCells() || jump.getEnd() > getTotalCells()) {
            throw new IllegalArgumentException("Jump positions exceed board size!");
        }
        cells[jump.getStart()].setJump(jump);
    }

}
