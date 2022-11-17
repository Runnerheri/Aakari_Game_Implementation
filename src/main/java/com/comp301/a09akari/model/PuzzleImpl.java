package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle{

    private int[][] board;
    public PuzzleImpl(int[][] board){
        this.board = board;
    }


    public int getWidth(){
        return board[0].length;
    }
    public int getHeight(){
        return board.length;
    }
    public CellType getCellType(int r, int c){
        if(r>= getWidth() || r<0 || c>= getHeight() || c<0){
            throw new IndexOutOfBoundsException();
        }
        if(board[r][c] == 0 || board[r][c] == 1 || board[r][c] == 2 || board[r][c] == 3 || board[r][c] == 4){
            return CellType.CLUE;
        }

        else if(board[r][c] == 5){
            return CellType.WALL;
        }

        else{
            return CellType.CORRIDOR;
        }
    }

    public int getClue(int r, int c){
        if(r> board[0].length || r<0 || c> board.length || c<0){
            throw new IndexOutOfBoundsException();
        }

        if(getCellType(r,c ) != CellType.CLUE){
            throw new IllegalArgumentException();
        }
        else{
            return board[r][c];
        }
    }
}
