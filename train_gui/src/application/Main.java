package application;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
  @FXML
  Canvas lineCanvas;
//  GraphicsContext gc;
  
  boolean running = true;

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Timeline Example");

    try {
      AnchorPane root = FXMLLoader.load(getClass().getResource("scenes/Main.fxml"));
      
      Canvas canvas = new Canvas(750, 430);
      root.getChildren().add(canvas);
      
      Scene theScene = new Scene(root);
      primaryStage.setScene(theScene);

      primaryStage.setResizable(false);

      GraphicsContext gc =  canvas.getGraphicsContext2D();

      final long startNanoTime = System.nanoTime();

      new AnimationTimer() {
        public void handle(long currentNanoTime) {
          double t = (currentNanoTime - startNanoTime) / 1000000000.0;

          // TESTE
          // TODO: Quero usar isso pra mudar a cor da aresta a depender do estado se a
          // aresta foi visitada ou não no percurso
          Color color = (currentNanoTime / 100000000) % 100 < 40 ? Color.YELLOW : Color.AQUA;
          gc.setStroke(color);
          gc.setLineWidth(4);
          gc.strokeLine(10, 0, 10, 300);
        }
      }.start();

      primaryStage.show();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
