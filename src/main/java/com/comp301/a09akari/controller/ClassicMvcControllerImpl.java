package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;

public class ClassicMvcControllerImpl implements ClassicMvcController{

    private Model model;
    public ClassicMvcControllerImpl(Model model){
        this.model = model;
    }
    public void clickNextPuzzle(){
        model.setActivePuzzleIndex(model.getActivePuzzleIndex() +1);
        model.getActivePuzzle();

    }
    public void clickPrevPuzzle(){
        model.setActivePuzzleIndex(model.getActivePuzzleIndex()-1);
        model.getActivePuzzle();
    }
    public void clickRandPuzzle(){

    }
    public void clickResetPuzzle(){
        model.resetPuzzle();
    }
    public void clickCell(int r, int c){
        model.addLamp(r,c);
    }
}
