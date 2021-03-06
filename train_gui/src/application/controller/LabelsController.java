package application.controller;

import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

import application.Main;
import graph.Edge;
import graph.Graph;
import graph.Node;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LabelsController {
  @FXML
  private static Label lblEstInicial, lblEstFinal, lblDist, lblTempo, lblBaldeacoes;
  
  public static void update(Node startNode, Node endNode, List<Node> path) {
    String res = "";
    res += "Esta??o inicial: " + startNode;
    res += "\nEsta??o final: " + endNode;
//    lblEstInicial.setText(startNode.toString());
//    lblEstFinal.setText(endNode.toString());

    var edges = Graph.nodesToEdges(Main.graph, path);
    res+= "\n\nEsta??es visitadas: " + path;
    res+= "\nArestas visitadas: \n";
    for (Edge edge : edges) {
      res+= "\t" + edge + "\n";
    }
    
    res+= "\n";
    
    
    int sum = 0;
    for (Edge edge : edges)
      sum += edge.getDistance();
    res += "\n\nDist?ncia: " + sum + " km";
//    lblDist.setText(sum + " km");

    float time = sum / 30f;
    LocalTime timeDay = LocalTime.ofSecondOfDay((long) (time * 3600));

    String lblRes = "BALDEA??ES:\n";
    Edge prevEdge = null;
    for (Edge currentEdge : edges) {
      if (prevEdge != null && prevEdge.getLine() != currentEdge.getLine()) {
        String baldeacoes = String.format("\t%-6s -> %6s (%s)%n", prevEdge.getLine(),
            currentEdge.getLine(), prevEdge.getBegin());
        lblRes += baldeacoes;
        timeDay = timeDay.plusMinutes(4);
      }
      prevEdge = currentEdge;
    }
    res += "\n\n" + lblRes;
//    lblBaldeacoes.setText(lblRes);

    String tempo = String.format("%02dh%02d%n", timeDay.getHour(), timeDay.getMinute());
    res += "\n\nTempo gasto: " + tempo;
//    lblTempo.setText(tempo);
    
    res+= "\n\n-----------------------------------------------\n\n";
    System.out.println(res);
  }
}