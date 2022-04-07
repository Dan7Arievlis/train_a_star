package application.controller;

import java.time.LocalTime;
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
    res += "Estação inicial: " + startNode;
    res += "\nEstação fina: " + endNode;
//    lblEstInicial.setText(startNode.toString());
//    lblEstFinal.setText(endNode.toString());

    var edges = Graph.nodesToEdges(Main.graph, path);

    int sum = 0;
    for (Edge edge : edges)
      sum += edge.getDistance();
    res += "\n\nDistância: " + sum + " km";
//    lblDist.setText(sum + " km");

    float time = sum / 30f;
    LocalTime timeDay = LocalTime.ofSecondOfDay((long) (time * 3600));

    String lblRes = "BALDEAÇÕES:\n";
    Edge prevEdge = null;
    for (Edge currentEdge : edges) {
      if (prevEdge != null && prevEdge.getLine() != currentEdge.getLine()) {
        String baldeacoes = String.format(" %-6s -> %6s na (%s)%n", prevEdge.getLine(),
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
    System.out.println(res);
  }
}