package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.util.Collections;
import java.util.Random;

public class ResetView {

  private AlternateMvcController controller;

  public ResetView(AlternateMvcController controller) {
    this.controller = controller;
  }

  Parent render() {
    HBox box = new HBox();

    Button reset_button = new Button("Reset");
    Button submit_button = new Button("Submit");
    Button next = new Button("Next");
    Button previous = new Button("Previous");
    Button random = new Button("Random");

    next.setOnAction(
        (ActionEvent event) -> {
          if (controller.getModel().getActivePuzzleIndex()
              == controller.getModel().getPuzzleLibrarySize()) {
            return;
          }
          controller
              .getModel()
              .setActivePuzzleIndex(controller.getModel().getActivePuzzleIndex() + 1);
        });

    previous.setOnAction(
        (ActionEvent event) -> {
          if (controller.getModel().getActivePuzzleIndex() == 0) {
            return;
          }
          controller
              .getModel()
              .setActivePuzzleIndex(controller.getModel().getActivePuzzleIndex() - 1);
        });

    random.setOnAction(
        (ActionEvent event) -> {
          int index = new Random().nextInt(4);
          controller.getModel().setActivePuzzleIndex(index);
        });

    reset_button.setOnAction(
        (ActionEvent actionEvent) -> {
          controller.getModel().resetPuzzle();
        });

    submit_button.setOnAction(
        (ActionEvent actionEvent) -> {
          boolean isSolved = controller.getModel().isSolved();
          if (isSolved == true) {
            Label label = new Label("Puzzle solved!");
            box.getChildren().add(label);
          }
          if (isSolved == false) {
            Label label = new Label("Incorrect :(");
            box.getChildren().add(label);
          }
        });

    box.getChildren().add(reset_button);
    box.getChildren().add(submit_button);
    box.getChildren().add(next);
    box.getChildren().add(previous);
    box.getChildren().add(random);
    return box;
  }
}
