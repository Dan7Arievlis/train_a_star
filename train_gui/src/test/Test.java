package test;

import graph.Graph;
import graph.Node;

public class Test {
  public static void main(String[] args) {
    var g = new Graph();
    
    g.findPath(Node.E10, Node.E14);
  }
}
