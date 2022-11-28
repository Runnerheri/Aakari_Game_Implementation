package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.Puzzle;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;

public class CellView {

  private AlternateMvcController controller;
  private Puzzle activePuzzle;
  private int i;
  private int j;

  public CellView(AlternateMvcController controller, Puzzle activePuzzle, int i, int j) {
    this.controller = controller;
    this.activePuzzle = activePuzzle;
    this.i = i;
    this.j = j;
  }

  public Parent render() {
    StackPane pane = new StackPane();
    TilePane tile = new TilePane();
    pane.getChildren().add(tile);

    if (controller.getActivePuzzle().getClue(i, j) == 0) {
      Label label = new Label("0");
      pane.getChildren().add(label);

    }
    if (controller.getActivePuzzle().getClue(i, j) == 1) {
      Label label = new Label("1");
      pane.getChildren().add(label);
    }
    if (controller.getActivePuzzle().getClue(i, j) == 2) {
      Label label = new Label("2");
      pane.getChildren().add(label);
    }
    if (controller.getActivePuzzle().getClue(i, j) == 3) {
      Label label = new Label("3");
      pane.getChildren().add(label);
    }
    if (controller.getActivePuzzle().getClue(i, j) == 4) {
      Label label = new Label("4");
      pane.getChildren().add(label);
    }
    if (controller.getActivePuzzle().getClue(i, j) == 5) {
      // make the tilepane white
    }
    if (controller.getActivePuzzle().getClue(i, j) == 6) {
      // make the tilepane black
    }

    return pane;


  }


}
