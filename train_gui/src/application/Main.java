package application;

import java.io.IOException;
import java.util.ArrayList;

import graph.Edge;
import graph.EdgeGUI;
import graph.Graph;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
  @FXML
  Canvas lineCanvas;
//  GraphicsContext gc;
  
  boolean running = true;
  public static Graph graph = new Graph();

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Train A*");

    try {
      AnchorPane root = FXMLLoader.load(getClass().getResource("scenes/Main.fxml"));
      AnchorPane stations = FXMLLoader.load(getClass().getResource("scenes/Stations.fxml"));
      AnchorPane labels = FXMLLoader.load(getClass().getResource("scenes/Labels.fxml"));
      
      Canvas canvas = new Canvas(750, 430);
      root.getChildren().add(canvas);
      root.getChildren().add(stations);
      root.getChildren().add(labels);
      
      Scene theScene = new Scene(root);
      primaryStage.setScene(theScene);

      primaryStage.setResizable(false);

      GraphicsContext gc =  canvas.getGraphicsContext2D();

//      final long startNanoTime = System.nanoTime();

      new AnimationTimer() {
        public void handle(long currentNanoTime) {
//          double t = (currentNanoTime - startNanoTime) / 1000000000.0;

          // TESTE
          for (EdgeGUI edge : graph.getListEdgesGUI())
            edge.draw(gc);
        }
      }.start();

      primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
