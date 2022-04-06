package graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import application.controller.LabelController;
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

  public Graph() {
    super();
    adjList = new TreeMap<>(new Comparator<Node>() {
      
      @Override
      public int compare(Node o1, Node o2) {
        var str1 = Integer.parseInt(o1.toString().substring(1));
        var str2 = Integer.parseInt(o2.toString().substring(1));
        return str1 - str2;
      }
    });
    
    mirrorMatrix();
    
    Node[] blueLine = {Node.E1, Node.E2, Node.E3, Node.E4, Node.E5, Node.E6};
    LinkedList<Node> list = new LinkedList<>();
    list.addAll(List.of(blueLine));
    connectLine(list, Line.BLUE, 11, 9, 7, 13, 3);
    
    
    Node[] redLine = {Node.E11, Node.E9, Node.E3, Node.E13};
    list = new LinkedList<>();
    list.addAll(List.of(redLine));
    connectLine(list, Line.RED, 12, 10, 18);
    
    Node[] greenLine = {Node.E12, Node.E8, Node.E4, Node.E13, Node.E14};
    list = new LinkedList<>();
    list.addAll(List.of(greenLine));
    connectLine(list, Line.GREEN, 7, 13, 11, 5);
    
    Node[] yellowLine = {Node.E10, Node.E2, Node.E9, Node.E8, Node.E5, Node.E7};
    list = new LinkedList<>();
    list.addAll(List.of(yellowLine));
    connectLine(list, Line.YELLOW, 4, 11, 9, 21, 2);

    System.out.println(this);
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
  
  public int getSize() {
    return adjList.size();
  }
  
  public void findPath(Node startNode, Node endNode) {
    var path = AStar.findPath(this, startNode, endNode);
    
    LabelController.update(startNode, endNode, path);
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

  private void connectLine(LinkedList<Node> stations, Line line, int... distances) {
    Node prevStation = null;
    int i = 0;
    for (Node currentStation : stations) {
      if (prevStation != null) {
        int distance = distances[i];
        addEdge(prevStation, currentStation, line, distance);
        i++;
      }
      prevStation = currentStation;
    }
  }

  private void addEdge(Node current, Node neighbour, Line line, int dist) {
    createEdge(current, neighbour, line, dist);
    createEdge(neighbour, current, line, dist);
  }
  
  public List<Node> getNeighbours(Node node){
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

  @Override
  public String toString() {
    String res = "";
    for (Node k : adjList.keySet())
      res += String.format("%-4s %s %s\n", k, "-", adjList.get(k).toString());
    return res;

  }
}