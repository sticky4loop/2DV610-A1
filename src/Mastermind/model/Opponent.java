package Mastermind.model;

import Mastermind.model.Board;

public class Opponent {

    private int rowLength;

    public Opponent() {
        this.rowLength = 1;
    }

    public Opponent(int rowLength) throws Exception {
        if(rowLength < Board.MINIMUM_ROW_LENGTH || rowLength > Board.MAXIMUM_ROW_LENGTH) {
            throw new Exception();
        }

        this.rowLength = rowLength;
    }

    public int getRowLength() {
        return this.rowLength;
    }
}
