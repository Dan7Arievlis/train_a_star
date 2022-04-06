package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Timeline Example");

    Group root = new Group();
    Scene theScene = new Scene(root);
    primaryStage.setScene(theScene);

    Canvas canvas = new Canvas(512, 512);
    root.getChildren().add(canvas);

    GraphicsContext gc = canvas.getGraphicsContext2D();

    final long startNanoTime = System.nanoTime();

    new AnimationTimer() {
      public void handle(long currentNanoTime) {
        double t = (currentNanoTime - startNanoTime) / 1000000000.0;
        
        // TESTE
        // TODO: Quero usar isso pra mudar a cor da aresta a depender do estado se a 
        // aresta foi visitada ou não no percurso
        Color color = (currentNanoTime / 100000000) % 2 == 0 ? Color.YELLOW : Color.AQUA;
        gc.setFill(color);
        gc.setLineWidth(8);
        gc.fillRect(10, 20, 10, 300);
      }
    }.start();

    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
