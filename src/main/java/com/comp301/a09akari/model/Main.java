package com.comp301.a09akari.model;

public class Main {

    public static void main(String[] args){
        int[][] PUZZLE_01 = {
                {1, 6, 5},
                {5, 6, 6},
                {6, 5, 1}


        };



        PuzzleLibrary library = new PuzzleLibraryImpl();
        Puzzle Bob = new PuzzleImpl(PUZZLE_01);
        library.addPuzzle(Bob);

        Model model = new ModelImpl(library);
        model.addLamp(2,0);
        model.addLamp(0,1);
        model.addLamp(1,2);
        //model.addLamp(2,3);
       // model.addLamp(3,6);

        System.out.println(model.isSolved());


    }
}
