package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.CellType;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import com.comp301.a09akari.model.Puzzle;

public class GameView {

  private AlternateMvcController controller;
  private int mouseClicked = 2;

  public GameView(AlternateMvcController controller) {

    this.controller = controller;
  }

  Parent render() {
    GridPane board = new GridPane();

    if (controller.getModel().isSolved()) {
      Label solved = new Label("Puzzle Solved!");
      board.getChildren().add(solved);
      return board;
    }

    board.setHgap(1);
    board.setVgap(1);

    board.getStyleClass().add("board");

    for (int i = 0; i < controller.getModel().getActivePuzzle().getHeight(); i++) {
      for (int j = 0; j < controller.getModel().getActivePuzzle().getWidth(); j++) {
        System.out.println(
            "index" + i + j + controller.getModel().getActivePuzzle().getCellType(i, j));
        board.add(makeTile(i, j), j, i);
      }
    }

    return board;
  }

  public TilePane makeTile(int i, int j) {
    TilePane tile = new TilePane();

    if (controller.getModel().getActivePuzzle().getCellType(i, j) == CellType.CLUE
        && controller.getModel().getActivePuzzle().getClue(i, j) == 0) {
      Label label = new Label("0");
      label.getStyleClass().add("text");
      tile.getChildren().add(label);

      if (controller.getModel().isClueSatisfied(i, j)) {
        tile.getStyleClass().add("satisfied-clue");
      } else {
        tile.getStyleClass().add("unsatisfied-clue");
      }
    } else if (controller.getModel().getActivePuzzle().getCellType(i, j) == CellType.CLUE
        && controller.getModel().getActivePuzzle().getClue(i, j) == 1) {
      Label label = new Label("1");
      label.getStyleClass().add("text");
      tile.getChildren().add(label);
      if (controller.getModel().isClueSatisfied(i, j)) {
        tile.getStyleClass().add("satisfied-clue");
      } else {
        tile.getStyleClass().add("unsatisfied-clue");
      }

    } else if (controller.getModel().getActivePuzzle().getCellType(i, j) == CellType.CLUE
        && controller.getModel().getActivePuzzle().getClue(i, j) == 2) {
      Label label = new Label("2");
      label.getStyleClass().add("text");
      tile.getChildren().add(label);
      if (controller.getModel().isClueSatisfied(i, j)) {
        tile.getStyleClass().add("satisfied-clue");
      } else {
        tile.getStyleClass().add("unsatisfied-clue");
      }
    } else if (controller.getModel().getActivePuzzle().getCellType(i, j) == CellType.CLUE
        && controller.getModel().getActivePuzzle().getClue(i, j) == 3) {
      Label label = new Label("3");
      label.getStyleClass().add("text");
      tile.getChildren().add(label);
      if (controller.getModel().isClueSatisfied(i, j)) {
        tile.getStyleClass().add("satisfied-clue");
      } else {
        tile.getStyleClass().add("unsatisfied-clue");
      }
    } else if (controller.getModel().getActivePuzzle().getCellType(i, j) == CellType.CLUE
        && controller.getModel().getActivePuzzle().getClue(i, j) == 4) {
      Label label = new Label("4");
      label.getStyleClass().add("text");
      tile.getChildren().add(label);
      if (controller.getModel().isClueSatisfied(i, j)) {
        tile.getStyleClass().add("satisfied-clue");
      } else {
        tile.getStyleClass().add("unsatisfied-clue");
      }
    } else if (controller.getModel().getActivePuzzle().getCellType(i, j) == CellType.CORRIDOR) {
      // Label label = new Label("");
      // tile.getChildren().add(label);
      if (controller.getModel().isLamp(i, j)) {
        if (controller.getModel().isLampIllegal(i, j)) {
          Label x = new Label("\u274C");
          tile.getChildren().add(x);
        } else {
          Label l = new Label("\u2705");
          tile.getChildren().add(l);
        }
      }
      if (controller.getModel().isLit(i, j)) {
        tile.getStyleClass().add("lit-tile");
      } else {
        tile.getStyleClass().add("corridor-tile");
      }
      /*
      tile.setOnMouseClicked((MouseEvent event) -> {
          Label l = new Label("\uD83D\uDCA1");
          tile.getChildren().add(l);
          controller.getModel().addLamp(i,j);


      });

       */
    } else if (controller.getModel().getActivePuzzle().getCellType(i, j) == CellType.WALL) {
      Label label = new Label("wall");
      label.getStyleClass().add("text");
      tile.getChildren().add(label);
      tile.getStyleClass().add("unsatisfied-clue");
    }

    tile.setOnMouseClicked(
        (MouseEvent event) -> {
          if (controller.getModel().getActivePuzzle().getCellType(i, j) == CellType.CORRIDOR) {
            if (controller.getModel().isLamp(i, j) == true) {
              controller.getModel().removeLamp(i, j);
            } else {
              controller.getModel().addLamp(i, j);
            }
          }
        });
    return tile;
  }
}
