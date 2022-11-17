package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {

    private PuzzleLibrary library;
    private int activePuzzle = 0;
    private List<ModelObserver> observers;

    private int[][] lampBoard;
    public ModelImpl(PuzzleLibrary library){
        if(library == null){
            throw new IllegalArgumentException();
        }
        this.library = library;
        lampBoard = new int[library.getPuzzle(activePuzzle).getHeight()][library.getPuzzle(activePuzzle).getWidth()];
        observers = new ArrayList<>();

    }

    public void addLamp(int r, int c){
        if(r<0 || r>= library.getPuzzle(activePuzzle).getHeight() || c<0 || c>=library.getPuzzle(activePuzzle).getWidth()){
            throw new IndexOutOfBoundsException();
        }
        if(library.getPuzzle(activePuzzle).getCellType(r,c) != CellType.CORRIDOR){
            throw new IllegalArgumentException();
        }
        lampBoard[r][c] = 1;

    }

    public void removeLamp(int r, int c){
        if(r<0 || r>= library.getPuzzle(activePuzzle).getHeight() || c<0 || c>=library.getPuzzle(activePuzzle).getWidth()){
            throw new IndexOutOfBoundsException();
        }
        if(library.getPuzzle(activePuzzle).getCellType(r,c) != CellType.CORRIDOR){
            throw new IllegalArgumentException();
        }
        lampBoard[r][c] = 0;
    }

    public boolean isLit(int r, int c){
        if(r<0 || r>= library.getPuzzle(activePuzzle).getHeight() || c<0 || c>=library.getPuzzle(activePuzzle).getWidth()){
            throw new IndexOutOfBoundsException();
        }
        if(library.getPuzzle(activePuzzle).getCellType(r,c) != CellType.CORRIDOR){
            throw new IllegalArgumentException();
        }
        Puzzle currentpuzzle = library.getPuzzle(activePuzzle);
        if(lampBoard[r][c] == 1){
            return true;
        }
        //loop through same row in either direction
        for(int i=c; i<lampBoard[r].length; i++){
            if(lampBoard[r][i] == 1){
                return true;
            }
            if(currentpuzzle.getCellType(r, i) == CellType.CLUE || currentpuzzle.getCellType(r,i) == CellType.WALL){
                return false;
            }
        }
        for(int i=c; i>=0; i--){
            if(lampBoard[r][i] ==1){
                return true;
            }
            if(currentpuzzle.getCellType(r, i) == CellType.CLUE || currentpuzzle.getCellType(r,i) == CellType.WALL){
                return false;
            }
        }
        //loop through the same column in either direction
        for(int j=r; j<lampBoard.length; j++){
            if(lampBoard[j][c] == 1){
                return true;
            }
            if(currentpuzzle.getCellType(j, c) == CellType.CLUE || currentpuzzle.getCellType(j,c) == CellType.WALL){
                return false;
            }
        }
        for(int j=r; j>=0; j--){
            if(lampBoard[j][c] == 1){
                return true;
            }
            if(currentpuzzle.getCellType(j, c) == CellType.CLUE || currentpuzzle.getCellType(j,c) == CellType.WALL){
                return false;
            }
        }
        return false;
    }

    public boolean isLamp(int r, int c){
        if(lampBoard[r][c] == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isLampIllegal(int r, int c){
        Puzzle currentPuzzle = library.getPuzzle(activePuzzle);
        if(r<0 || r>= library.getPuzzle(activePuzzle).getHeight() || c<0 || c>=library.getPuzzle(activePuzzle).getWidth()){
            throw new IndexOutOfBoundsException();
        }
        if(lampBoard[r][c] != 1){
            throw new IllegalArgumentException();
        }
        //check if another lamp is in the same row, and if a wall is there
        boolean lampinsamerow = false;
        boolean wallinsamerow = false;
        for(int i=0; i< currentPuzzle.getWidth(); i++){
            if(lampBoard[r][i] ==1){
                lampinsamerow = true;
            }
            if(currentPuzzle.getCellType(r, i) == CellType.CLUE || currentPuzzle.getCellType(r,i) == CellType.WALL){
                wallinsamerow = true;
            }
        }
        //check if another lamp exists in the same column

        boolean lampinsamecolumn = false;
        boolean wallinsamecolumn = false;
        for(int j =0; j<currentPuzzle.getHeight(); j++ ){
            if(lampBoard[j][c] ==1){
                lampinsamecolumn = true;
            }
            if(currentPuzzle.getCellType(j, c) == CellType.CLUE || currentPuzzle.getCellType(j,c) == CellType.WALL){
                wallinsamecolumn = true;
            }
        }

        if(lampinsamecolumn && lampinsamerow == true){
            if(wallinsamecolumn == false && wallinsamerow == false){
                return true;
            }
            if(wallinsamerow == true){
            }
        }
        return false;
    }

    public Puzzle getActivePuzzle(){
        return library.getPuzzle(activePuzzle);
    }

    public int getActivePuzzleIndex(){
        return activePuzzle;
    }

    public void setActivePuzzleIndex(int index){
        if(index <0 || index >= library.size()){
            throw new IndexOutOfBoundsException();
        }
        activePuzzle = index;
        lampBoard = new int[library.getPuzzle(index).getHeight()][library.getPuzzle(index).getWidth()];

    }

    public int getPuzzleLibrarySize(){
         return library.size();
    }

    public void resetPuzzle(){
        Puzzle activePuzzle = library.getPuzzle(getActivePuzzleIndex());
        int width = activePuzzle.getWidth();
        int height = activePuzzle.getHeight();
        lampBoard = new int[height][width];
        //notifyobservers
        }

    public boolean isSolved(){
        //double for loop to loop through each cell
        //check if cell type is clue
            //check if clue is satisfied, it not return false
        //check if cell is corridor
            //check if it is not lit OR it is an illegal lamp, return false
        //return true at end as default

        return false;
    }


    public boolean isClueSatisfied(int r, int c){
        Puzzle currentPuzzle = library.getPuzzle(activePuzzle);
        if(r<0 || r>= library.getPuzzle(activePuzzle).getHeight() || c<0 || c>=library.getPuzzle(activePuzzle).getWidth()){
            throw new IndexOutOfBoundsException();
        }
        if(currentPuzzle.getCellType(r,c) != CellType.CLUE){
            throw new IllegalArgumentException();
        }

        int count = 0;
        if(lampBoard[r+1][c] == 1){
            count +=1;
        }
        if(lampBoard[r-1][c] ==1){
            count +=1;
        }
        if(lampBoard[r][c+1] ==1){
            count +=1;
        }
        if(lampBoard[r][c-1] == 1){
            count +=1;
        }
        int clue = currentPuzzle.getClue(r,c);

        if(clue == count){
           return true;
        }
        else{
            return false;
        }


    }

    public void addObserver(ModelObserver observer){
        observers.add(observer);
    }

    public void removeObserver(ModelObserver observer){
        observers.remove(observer);
    }

    public void notifyObservers(){

    }
}
