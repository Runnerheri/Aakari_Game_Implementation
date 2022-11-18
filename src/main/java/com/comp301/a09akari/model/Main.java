package com.comp301.a09akari.model;

public class Main {

    public static void main(String[] args){
        int[][] PUZZLE_01 = {
                {6, 6, 6, 1, 6, 6, 6},
                {6, 6, 6, 6, 6, 6, 6},
                {0, 6, 6, 6, 6, 6, 6},
                {6, 6, 6, 6, 6, 6, 6}

        };



        PuzzleLibrary library = new PuzzleLibraryImpl();
        Puzzle Bob = new PuzzleImpl(PUZZLE_01);
        library.addPuzzle(Bob);

        Model model = new ModelImpl(library);
        model.addLamp(0,0);
        model.addLamp(0,4);
        model.addLamp(1,2);
        model.addLamp(2,3);
        model.addLamp(3,6);

        System.out.println(model.isSolved());


    }
}
