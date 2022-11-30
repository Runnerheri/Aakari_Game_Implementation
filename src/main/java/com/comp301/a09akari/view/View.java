package com.comp301.a09akari.view;

import com.comp301.a09akari.controller.AlternateMvcController;
import com.comp301.a09akari.model.Model;
import com.comp301.a09akari.model.ModelObserver;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View implements ModelObserver {

  private AlternateMvcController controller;
  private Stage stage;

  public View(AlternateMvcController controller, Stage stage) {
    this.controller = controller;
    this.stage = stage;
  }

  public Parent render() {
    VBox layout = new VBox();

    GameView game_view = new GameView(controller);
    ResetView reset_view = new ResetView(controller);
    PuzzleIndexView index_view = new PuzzleIndexView(controller);

    layout.getChildren().add(index_view.render());
    layout.getChildren().add(game_view.render());
    layout.getChildren().add(reset_view.render());

    return layout;
  }

  public void update(Model model) {
    Scene scene = new Scene(render());

    scene.getStylesheets().add("main.css");

    stage.setScene(scene);
    stage.sizeToScene();
  }
}
