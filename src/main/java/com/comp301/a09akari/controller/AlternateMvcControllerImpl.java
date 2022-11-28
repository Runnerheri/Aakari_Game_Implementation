package com.comp301.a09akari.controller;

import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.Puzzle;

public class AlternateMvcControllerImpl implements AlternateMvcController{

   private Model model;

   public AlternateMvcControllerImpl(Model model){
       this.model = model;
   }

   public Model getModel(){
       return model;
   }

    @Override
    public void clickNextPuzzle() {

    }

    @Override
    public void clickPrevPuzzle() {

    }

    @Override
    public void clickRandPuzzle() {

    }

    @Override
    public void clickResetPuzzle() {
        model.resetPuzzle();
    }

    @Override
    public void clickCell(int r, int c) {

    }

    @Override
    public boolean isLit(int r, int c) {
       return isLit(r, c);
    }

    @Override
    public boolean isLamp(int r, int c) {
       return model.isLamp(r,c);
    }

    @Override
    public boolean isClueSatisfied(int r, int c) {
       return model.isClueSatisfied(r, c);
    }

    @Override
    public boolean isSolved() {
       return model.isSolved();
    }

    @Override
    public Puzzle getActivePuzzle() {
       return model.getActivePuzzle();
    }
}
