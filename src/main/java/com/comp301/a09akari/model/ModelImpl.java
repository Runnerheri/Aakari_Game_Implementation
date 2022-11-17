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
        if(r<0 || r>= library.getPuzzle(activePuzzle).getWidth() || c<0 || c>=library.getPuzzle(activePuzzle).getHeight()){
            throw new IndexOutOfBoundsException();
        }
        if(library.getPuzzle(activePuzzle).getCellType(r,c) != CellType.CORRIDOR){
            throw new IllegalArgumentException();
        }
        lampBoard[r][c] = 1;

    }

    public void removeLamp(int r, int c){
        if(r<0 || r>= library.getPuzzle(activePuzzle).getWidth() || c<0 || c>=library.getPuzzle(activePuzzle).getHeight()){
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

        boolean isLit = false;
        //check if the cell itself contains a lamp
        if(lampBoard[r][c] == 1){
            return true;
        }

        boolean lampInRow = false;
        boolean lampInColumn = false;
        boolean wallinrow = false;
        boolean wallincolumn = false;
        int wallrowposition = 0;
        int wallcolumnposition =0;
        int lamprowposition = 0;
        int lampcolumnposition= 0;

        //check if a lamp exists in the same row using for loop. Then check if a wall exists.
        for(int i =0; i< lampBoard.length; i++){
            if(lampBoard[r][i] == 1){
                lampInRow = true;
                lamprowposition = i;
            }
            //check if a wall exists in the same row
            //if so, store its position in an index
            if(library.getPuzzle(activePuzzle).getCellType(r,i) == CellType.WALL || library.getPuzzle(activePuzzle).getCellType(r,c) == CellType.CLUE){
                wallinrow = true;
                wallrowposition = i;
            }
        }
        //check if lamp exists in the same column using for loop. Then check if a wall exists
        //if a lamp exists, store its position in an index
        for(int j=0; j<lampBoard[j].length; j++){
            if(lampBoard[j][c] == 1){
                lampInColumn = true;
                lampcolumnposition = j;
            }
            //check if a wall exists in the same column
            //if so, store its position in an index
            if(library.getPuzzle(activePuzzle).getCellType(j,c) == CellType.WALL || library.getPuzzle(activePuzzle).getCellType(j,c) == CellType.CLUE){
                wallincolumn = true;
                wallcolumnposition = j;

            }
        }

        //if no lamp exists in the same row or column, just return false
        if(lampInRow == false && lampInColumn == false){
            isLit = false;
        }

        //if a lampInRow AND lampInColumn is true, check for walls in both the row and the column
        if(lampInRow == true && lampInColumn == true){
            if(wallinrow == false || wallincolumn == false){
                isLit = true;
            }
            if(wallinrow == true){
                if(lamprowposition < c && c < wallrowposition || wallrowposition <c && c<lamprowposition){
                    isLit = true;
                }
            }
            else if(wallincolumn == true){
                if(lampcolumnposition < r && r < wallcolumnposition || wallcolumnposition < r && r < lampcolumnposition){
                    isLit = true;
                }

            }
            else{
                isLit = false;
            }
        }


        //if lampInRow is true, check for walls
        else if(lampInRow == true){
            if(wallinrow == false){
                isLit = true;
            }
            else{
                if(lamprowposition < c && c < wallrowposition || wallrowposition <c && c<lamprowposition){
                    isLit = true;
                }
                else{
                    isLit = false;
                }
            }
        }
        //if lampInColumn is true, check for walls
        if(lampInColumn == true){
            if(wallincolumn == false){
                isLit = true;
            }
            else{
                if(lampcolumnposition < r && r < wallcolumnposition || wallcolumnposition < r && r < lampcolumnposition){
                    isLit = true;
                }
                else{
                    isLit = false;
                }
            }
        }
        return isLit;
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
        if(r<0 || r>= library.getPuzzle(activePuzzle).getWidth() || c<0 || c>=library.getPuzzle(activePuzzle).getHeight()){
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
        return false;
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
