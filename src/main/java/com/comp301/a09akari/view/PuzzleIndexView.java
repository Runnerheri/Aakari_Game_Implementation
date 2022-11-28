package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;


public class PuzzleIndexView {

    private AlternateMvcController controller;

    public PuzzleIndexView(AlternateMvcController controller){
        this.controller = controller;
    }

    Parent render(){
        StackPane pane = new StackPane();

        Label label = new Label("Puzzle Index: " + (controller.getModel().getActivePuzzleIndex()+1));
        //TO-DO write code to display correct index

        pane.getChildren().add(label);

        return pane;

    }

}


