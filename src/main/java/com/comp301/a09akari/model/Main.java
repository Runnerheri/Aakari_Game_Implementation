package com.comp301.a09akari.model;

public class Main {

    public static void main(String[] args){
        int[][] PUZZLE_01 = {
                {6, 6, 2, 6, 6, 2, 6, 6},
                {6, 3, 6, 6, 6, 6, 6, 6},
                {6, 6, 5, 5, 6, 3, 6, 0},
                {6, 6, 6, 6, 6, 6, 5, 6},
                {6, 5, 6, 6, 6, 6, 6, 6},
                {2, 6, 3, 6, 5, 5, 6, 6},
                {6, 6, 6, 6, 6, 6, 3, 6},
                {6, 6, 5, 6, 6, 2, 6, 6}




        };


        PuzzleLibrary library = new PuzzleLibraryImpl();
        Puzzle Bob = new PuzzleImpl(PUZZLE_01);
        library.addPuzzle(Bob);

        Model model = new ModelImpl(library);
        model.addLamp(0,1);
        model.addLamp(0,3);
        model.addLamp(0,6);
        model.addLamp(1,0);
        model.addLamp(1,5);

        model.addLamp(2,1);
        model.addLamp(2,4);
        model.addLamp(3,5);
        model.addLamp(4,2);
        model.addLamp(5,1);

        model.addLamp(5,3);
        model.addLamp(5,6);
        model.addLamp(6,0);
        model.addLamp(6,7);
        model.addLamp(7,4);
        model.addLamp(7,6);

        System.out.println(model.isSolved());


        for(int i=0; i< Bob.getHeight(); i++){
            for(int j=0; j<Bob.getWidth(); j++){
                if(Bob.getCellType(i,j) == CellType.CORRIDOR){
                    System.out.println("Index i, j isLit =" + model.isLit(i,j));
                }
            }
        }
    }


}
