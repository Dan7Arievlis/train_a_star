package application.controller;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import application.Main;
import graph.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class StationsController {
  @FXML
  private RadioButton E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14;
  private BlockingQueue<Node> queue = new LinkedBlockingQueue<>(2);

  public void select(ActionEvent event) {
    RadioButton[] buttonArray= {E1, E2, E3, E4, E5, E6, E7, E8, E9, E10, E11, E12, E13, E14};
    
    RadioButton button = (RadioButton) event.getSource();
    String station = button.getId();
    Node stationNode = null;
    for (Node node : Main.graph.getNodes()) {
      if (node.getName().equals(station)) {
        stationNode = node;
        break;
      }
    }

    if (button.isSelected()) {
      if (queue.size() == 1) {
        try {
          Node fst = queue.take();
          Main.graph.findPath(fst, stationNode);
          for (RadioButton rb : buttonArray)
            rb.setSelected(false);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }else {
        queue.add(stationNode);
      }
    }
  }
}
