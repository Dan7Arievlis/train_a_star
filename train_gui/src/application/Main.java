package application;

import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {
  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Timeline Example");

    try {
      AnchorPane root = FXMLLoader.load(getClass().getResource("scenes/Main.fxml"));

      Scene theScene = new Scene(root);
      primaryStage.setScene(theScene);

      Canvas canvas = new Canvas(root.getWidth(), root.getHeight());
      primaryStage.setResizable(false);
      
      GraphicsContext gc = canvas.getGraphicsContext2D();

      final long startNanoTime = System.nanoTime();

      new AnimationTimer() {
        public void handle(long currentNanoTime) {
          double t = (currentNanoTime - startNanoTime) / 1000000000.0;

          // TESTE
          // TODO: Quero usar isso pra mudar a cor da aresta a depender do estado se a
          // aresta foi visitada ou não no percurso
          Color color = (currentNanoTime / 100000000) % 100 < 40 ? Color.YELLOW : Color.AQUA;
          gc.setFill(color);
          gc.setLineWidth(4);
          gc.fillRect(10, 20, 10, 300);
        }
      }.start();

      primaryStage.show();
      root.getChildren().add(canvas);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
