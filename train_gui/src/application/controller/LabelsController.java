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
  private Label lblEstInicial, lblEstFinal, lblDist, lblTempo, lblBaldeacoes;
  
  @SuppressWarnings("exports")
  public void update(Node startNode, Node endNode, List<Node> path) {
    System.out.println("Estações do caminho: " + path);
    lblEstInicial.setText(startNode.toString());
//    lblEstFinal.setText(endNode.toString());
    
    var edges = Graph.nodesToEdges(Main.graph, path);
    System.out.println("Linhas: " + edges + "\n");
    
    int sum = 0;
    for (Edge edge : edges) 
      sum+=edge.getDistance();
    System.out.println(sum + " Quilômetros\n");
//    lblDist.setText(sum + " km");
    
    float time = sum / 30f;
    LocalTime timeDay = LocalTime.ofSecondOfDay((long)(time * 3600));
    
    String lblRes = "";
    Edge prevEdge = null;
    for (Edge currentEdge : edges) {
      if (prevEdge != null && prevEdge.getLine() != currentEdge.getLine()) {
        String baldeacoes = String.format("BALDEAÇÃO entre as linhas: %-6s -> %6s na Estação %s%n", 
            prevEdge.getLine(), currentEdge.getLine(), prevEdge.getBegin());
        System.out.format(baldeacoes);
        lblRes += baldeacoes;
        timeDay = timeDay.plusMinutes(4);
      }
      prevEdge = currentEdge;
    }
//    lblBaldeacoes.setText(lblRes);
    
    
    String tempo = String.format("%02dh%02d%n", timeDay.getHour(), timeDay.getMinute());
    System.out.println("\nTempo total gasto = " + tempo);
//    lblTempo.setText(tempo);
  }
}
