package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import application.Main;
import application.controller.LabelsController;
import pathfinding.AStar;

public class Graph {
  public static int[][] distanceMatrix = {
      {0, 11, 20, 27, 40, 43, 39, 28, 18, 10, 18, 30, 30, 32},
      {0,  0,  9, 16, 29, 32, 28, 19, 11,  4, 17, 23, 21, 24},
      {0,  0,  0,  7, 20, 22, 19, 15, 10, 11, 21, 21, 13, 18},
      {0,  0,  0,  0, 13, 16, 12, 13, 13, 18, 26, 21, 11, 17},
      {0,  0,  0,  0,  0,  3,  2, 21, 25, 31, 38, 27, 16, 20},
      {0,  0,  0,  0,  0,  0,  4, 23, 28, 33, 41, 30, 17, 20},
      {0,  0,  0,  0,  0,  0,  0, 22, 25, 29, 38, 28, 13, 17},
      {0,  0,  0,  0,  0,  0,  0,  0,  9, 22, 18,  7, 25, 30},
      {0,  0,  0,  0,  0,  0,  0,  0,  0, 13, 12, 12, 23, 28},
      {0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 20, 27, 20, 23},
      {0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 15, 35, 39},
      {0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 31, 37},
      {0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  5},
      {0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
    };
  private Map<Node, ArrayList<Edge>> adjList;
  private List<EdgeGUI> listEdgeGUI;

  public Graph() {
    super();
    listEdgeGUI = new ArrayList<>();
    adjList = new TreeMap<>(new Comparator<Node>() {

      @Override
      public int compare(Node o1, Node o2) {
        var str1 = Integer.parseInt(o1.toString().substring(1));
        var str2 = Integer.parseInt(o2.toString().substring(1));
        return str1 - str2;
      }
    });

    mirrorMatrix();
//    printMatrix();

    Node[] blueLine = {Node.E1, Node.E2, Node.E3, Node.E4, Node.E5, Node.E6};
    LinkedList<Node> list = new LinkedList<>();
    list.addAll(List.of(blueLine));
    double[][] blueX = {{33, 82}, {82, 129, 156}, {156, 213, 248, 281}, {284, 362}, {362, 375, 421, 425, 433}};
    double[][] blueY = {{139, 177}, {177, 211, 211}, {211, 211, 238, 238}, {236, 290}, {290, 300, 266, 266, 273}};
    connectLine(list, blueX, blueY, Line.BLUE, 11, 9, 7, 13, 3);

    Node[] redLine = {Node.E11, Node.E9, Node.E3, Node.E13};
    list = new LinkedList<>();
    list.addAll(List.of(redLine));
    double[][] redX = {{126, 194, 194}, {194, 194, 156}, {154, 154, 201, 218}};
    double[][] redY = {{84, 131, 148}, {148, 185, 213}, {211, 282, 317, 317}};
    connectLine(list, redX, redY, Line.RED, 12, 10, 18);

    Node[] greenLine = {Node.E12, Node.E8, Node.E4, Node.E13, Node.E14};
    list = new LinkedList<>();
    list.addAll(List.of(greenLine));
    double[][] greenX = {{261, 323, 323}, {323, 323, 280, 280}, {279, 258, 258, 223, 223}, {219, 219, 188}};
    double[][] greenY = {{76, 120, 150}, {150, 170, 200, 240}, {244, 261, 277, 277, 314}, {321, 337, 360}};
    connectLine(list, greenX, greenY, Line.GREEN, 7, 13, 11, 5);

    Node[] yellowLine = {Node.E10, Node.E2, Node.E9, Node.E8, Node.E5, Node.E7};
    list = new LinkedList<>();
    list.addAll(List.of(yellowLine));
    double[][] yellowX = {{48, 80}, {84, 127, 192}, {196, 324}, {324, 364, 382, 382, 362, 362}, {360, 344, 344, 322}};
    double[][] yellowY = {{199, 176}, {174, 144, 144}, {144, 144}, {144, 144, 158, 184, 200, 292}, {295, 307, 323, 339}};
    connectLine(list, yellowX, yellowY, Line.YELLOW, 4, 11, 9, 21, 2);
  }

  public void printMatrix() {
    for (int i = 0; i < distanceMatrix.length; i++) {
    for (int j = 0; j < distanceMatrix.length; j++) {
      System.out.format("%02d ", distanceMatrix[i][j]);
    }
    System.out.println();
  }
  }

  public Map<Node, ArrayList<Edge>> getAdjList() {
    return adjList;
  }

  public Set<Node> getNodes() {
    return adjList.keySet();
  }

  public Collection<ArrayList<Edge>> getEdges() {
    return adjList.values();
  }

  public List<EdgeGUI> getListEdgesGUI() {
    return this.listEdgeGUI;
  }

  public int getSize() {
    return adjList.size();
  }

  public void findPath(Node startNode, Node endNode) {
    var path = AStar.findPath(this, startNode, endNode);
    for (EdgeGUI edgeGUI : listEdgeGUI) {
      edgeGUI.setActive(false);
    }

    var edges = Graph.nodesToEdges(Main.graph, path);
    for (Edge edge : edges) {
      this.setActiveEdge(edge.getBegin(), edge.getEnd());
    }

    LabelsController.update(startNode, endNode, path);
  }

  private void setActiveEdge(Node begin, Node end) {
    Set<Node> nodes = Set.of(begin, end);
    for (EdgeGUI edgeGUI : listEdgeGUI) {
      if (nodes.equals(edgeGUI.nodes))
        edgeGUI.setActive(true);
    }
  }

  public int getDistance(Node nodeA, Node nodeB) {
    int i = Integer.parseInt(nodeA.toString().substring(1)) - 1;
    int j = Integer.parseInt(nodeB.toString().substring(1)) - 1;

    return distanceMatrix[i][j];
  }

  private void mirrorMatrix() {
    for (int i = 0; i < distanceMatrix.length; i++) {
      for (int j = i + 1; j < distanceMatrix.length; j++) {
        distanceMatrix[j][i] = distanceMatrix[i][j];
      }
    }
  }

  private void connectLine(LinkedList<Node> stations, double[][] pointsX, double[][] pointsY, Line line,
      int... distances) {
    Node prevStation = null;
    int i = 0;
    for (Node currentStation : stations) {
      if (prevStation != null) {
        int distance = distances[i];
        double[] x = pointsX[i];
        double[] y = pointsY[i];
        addEdge(prevStation, currentStation, line, distance, x, y);
        i++;
      }
      prevStation = currentStation;
    }
  }

  private void addEdge(Node current, Node neighbour, Line line, int dist, double[] xPoints, double[] yPoints) {
    createEdge(current, neighbour, line, dist);
    createEdge(neighbour, current, line, dist);
    listEdgeGUI.add(new EdgeGUI(current, neighbour, line, xPoints, yPoints));
  }

  public List<Node> getNeighbours(Node node) {
    var edges = this.adjList.get(node);
    List<Node> neighbours = new LinkedList<>();
    edges.stream().forEach(e -> neighbours.add(e.getBegin()));
    return neighbours;
  }

  private void createEdge(Node current, Node neighbour, Line line, int dist) {
    var list = adjList.get(current);
    if (list == null)
      list = new ArrayList<>();
    list.add(new Edge(neighbour, dist, current, line));
    adjList.put(current, list);
  }

  public static List<Edge> nodesToEdges(Graph graph, List<Node> path) {
    List<Edge> edges = new ArrayList<>();
    Node prevStation = null;
    for (Node currentStation : path) {
      if (prevStation != null) {
        for (Edge e : graph.getAdjList().get(prevStation)) {
          if (e.getBegin() == currentStation) {
            edges.add(e);
            continue;
          }
        }
      }
      prevStation = currentStation;
    }

    return edges;
  }

  @Override
  public String toString() {
    String res = "";
    for (Node k : adjList.keySet())
      res += String.format("%-4s %s %s\n", k, "-", adjList.get(k).toString());
    return res;

  }
}