package org.example.snakeandladdergame.model;

import org.example.snakeandladdergame.enums.JumpType;

public class Jump {
    private int start;
    private int end;
    private JumpType type;

    public Jump(int start, int end) {
        if(start == end)  throw new IllegalArgumentException("Jump cannot start and end at same cell");
        this.start = start;
        this.end = end;
        if(start > end){
            this.type = JumpType.SNAKE;
        }
        else if(end> start){
            this.type = JumpType.LADDER;
        }
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public JumpType getType() {
        return type;
    }

    @Override
    public String toString() {
        switch (type) {
            case SNAKE: return "Snake from " + start + " to " + end;
            case LADDER: return "Ladder from " + start + " to " + end;
            default: return "Jump from " + start + " to " + end;
        }
    }

}
