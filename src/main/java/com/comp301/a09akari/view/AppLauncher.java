package com.comp301.a09akari.view;

import com.comp301.a09akari.model.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.comp301.a09akari.controller.AlternateMvcControllerImpl;

public class AppLauncher extends Application {

  public static void main(String[] args) {
    launch();
  }

  public void start(Stage stage) {
    // TODO: Create your Model, View, and Controller instances and launch your GUI

    int[][] board3 = {
      {6, 6, 5, 6, 6, 6, 6},
      {6, 5, 6, 6, 6, 4, 6},
      {6, 6, 6, 6, 6, 6, 5},
      {6, 6, 6, 6, 6, 6, 6},
      {3, 6, 6, 6, 6, 6, 6},
      {6, 2, 6, 6, 6, 5, 6},
      {6, 6, 6, 6, 0, 6, 6},
    };

    int[][] board2 = {
      {5, 6, 6, 5, 6, 6, 6, 6, 6, 5},
      {6, 6, 6, 6, 6, 6, 6, 5, 6, 6},
      {6, 3, 6, 6, 6, 6, 0, 6, 6, 6},
      {6, 6, 2, 6, 6, 5, 6, 6, 6, 1},
      {6, 6, 6, 1, 0, 5, 6, 6, 6, 6},
      {6, 6, 6, 6, 1, 5, 5, 6, 6, 6},
      {5, 6, 6, 6, 2, 6, 6, 2, 6, 6},
      {6, 6, 6, 5, 6, 6, 6, 6, 5, 6},
      {6, 6, 1, 6, 6, 6, 6, 6, 6, 6},
      {0, 6, 6, 6, 6, 6, 1, 6, 6, 0},
    };

    int[][] board1 = {
      {6, 6, 6, 6, 1, 6, 6},
      {6, 6, 6, 5, 6, 6, 6},
      {0, 6, 6, 6, 6, 6, 6},
      {6, 5, 6, 6, 6, 4, 6},
      {6, 6, 6, 6, 6, 6, 5},
      {6, 6, 6, 2, 6, 6, 6},
      {6, 6, 5, 6, 6, 6, 6},
    };

    int[][] board4 = {
      {6, 1, 6, 6, 6, 6, 5, 6, 6, 6},
      {6, 6, 6, 6, 6, 6, 6, 6, 6, 5},
      {6, 6, 5, 5, 6, 6, 6, 2, 6, 6},
      {2, 6, 6, 5, 6, 6, 1, 5, 6, 6},
      {6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
      {6, 6, 6, 6, 6, 6, 6, 6, 6, 6},
      {6, 6, 5, 2, 6, 6, 0, 6, 6, 1},
      {6, 6, 2, 6, 6, 6, 5, 1, 6, 6},
      {2, 6, 6, 6, 6, 6, 6, 6, 6, 6},
      {6, 6, 6, 5, 6, 6, 6, 6, 5, 6},
    };

    int[][] board5 = {
      {6, 6, 5, 6, 6, 6},
      {6, 5, 6, 6, 6, 3},
      {6, 6, 6, 6, 6, 6},
      {6, 6, 6, 6, 6, 6},
      {3, 6, 6, 6, 6, 6},
      {6, 2, 6, 6, 6, 6},
      {6, 6, 6, 6, 0, 6},
    };
    PuzzleLibrary library = new PuzzleLibraryImpl();

    Puzzle puzzle1 = new PuzzleImpl(board1);
    library.addPuzzle(puzzle1);

    Puzzle puzzle2 = new PuzzleImpl(board2);
    library.addPuzzle(puzzle2);

    Puzzle puzzle3 = new PuzzleImpl(board3);
    library.addPuzzle(puzzle3);

    Puzzle puzzle4 = new PuzzleImpl(board4);
    library.addPuzzle(puzzle4);

    Puzzle puzzle5 = new PuzzleImpl(board5);
    library.addPuzzle(puzzle5);

    Model model = new ModelImpl(library);
    AlternateMvcControllerImpl controller = new AlternateMvcControllerImpl(model);

    View view = new View(controller, stage);
    model.addObserver(view);

    Scene scene = new Scene(view.render());
    scene.getStylesheets().add("main.css");

    stage.setScene(scene);
    stage.show();
  }
}
