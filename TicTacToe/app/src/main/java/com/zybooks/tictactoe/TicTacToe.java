package com.zybooks.tictactoe;

public class TicTacToe {
    public static final int GRID_SIZE = 3;
    private int Turn = 1;

    private boolean xWin = false;
    private boolean oWin = false;
    private boolean catGame = false;

    private final int[][] mGameGrid;

    public void nextTurn() {
        Turn = Turn + 1;
    }

    public TicTacToe() {
        mGameGrid = new int[GRID_SIZE][GRID_SIZE];
    }

    public boolean did_xWin(){
        return xWin;
    }
    public boolean did_oWin(){
        return oWin;
    }

    public boolean didCatGame(){
        return catGame;
    }

    public void newGame() {
        Turn = 1;
        xWin = false;
        oWin = false;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                mGameGrid[row][col] = 0;
            }
        }
    }

    public int isSquare(int row, int col) {
        return mGameGrid[row][col];
    }

    public void selectSquare(int row, int col) {

        mGameGrid[row][col] = Turn;
    }

    public int mTurn() {
        return Turn;
    }

    public void noTurns(){
        Turn = 10;
    }
    public boolean isGameOver() {
        //Check for wins in rows
        for (int row = 0; row < GRID_SIZE; row++) {
            int checkRowProduct = mGameGrid[row][0] * mGameGrid[row][1] * mGameGrid[row][2];
            if ( (mGameGrid[row][0] % 2 == mGameGrid[row][1] % 2) && (mGameGrid[row][0] % 2 == mGameGrid[row][2] % 2) ) {
                if (checkRowProduct != 0) {
                    if (mGameGrid[row][0] % 2 == 1){
                        xWin = true;
                    }
                    else {
                        oWin = true;
                    }
                    return true;
                }
            }
        }
        //Check for wins in rows
        for (int col = 0; col < GRID_SIZE; col++) {
            int checkColProduct = mGameGrid[0][col] * mGameGrid[1][col] * mGameGrid[2][col];
            if ( (mGameGrid[0][col] % 2 == mGameGrid[1][col] % 2) && (mGameGrid[0][col] % 2 == mGameGrid[2][col] % 2) ) {
                if (checkColProduct != 0) {
                    if (mGameGrid[0][col] % 2 == 1){
                        xWin = true;
                    }
                    else {
                        oWin = true;
                    }
                    return true;
                }
            }
        }
        //Check for win diagonally upper left to lower right
        int DiagUpperLeftProduct = mGameGrid[0][0] * mGameGrid[1][1] * mGameGrid[2][2];
        if( (mGameGrid[0][0] % 2 == mGameGrid[1][1] % 2) && (mGameGrid[0][0] % 2 == mGameGrid[2][2] %2) ) {
            if (DiagUpperLeftProduct != 0){
                if (mGameGrid[0][0] % 2 == 1){
                    xWin = true;
                }
                else {
                    oWin = true;
                }
                return true;
            }
        }
        //Check for win diagonally lower left to upper right
        int DiagLowerLeftProduct = mGameGrid[2][0] * mGameGrid[1][1] * mGameGrid[0][2];
        if( (mGameGrid[2][0] % 2 == mGameGrid[1][1] % 2) && (mGameGrid[2][0] % 2 == mGameGrid[0][2] %2) ) {
            if (DiagLowerLeftProduct != 0){
                if (mGameGrid[2][0] % 2 == 1){
                    xWin = true;
                }
                else {
                    oWin = true;
                }
                return true;
            }
        }
        //Cat Game
        if (Turn >= 9) {
            catGame = true;
            return true;
        }
        return false;
    }
}