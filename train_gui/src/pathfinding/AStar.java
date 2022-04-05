
package pathfinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import graph.Graph;
import graph.Node;

public class AStar {
  public static List<Node> findPath(Graph graph, Node startNode, Node targetNode) {
    Heap openSet = new Heap(graph.getSize());
    Set<Node> closedSet = new HashSet<>();

    openSet.add(startNode);

    while (openSet.count() > 0) {
      Node currentNode = openSet.remove();
      closedSet.add(currentNode);
      
      if(currentNode == targetNode)
        return retracePath(startNode, targetNode);

      for (Node neighbour : graph.getNeighbours(currentNode)) {
        if(!neighbour.isWalkable() || closedSet.contains(neighbour))
          continue;
        
        int newCostToNeighbour = currentNode.getgCost() + graph.getDistance(currentNode, neighbour);
        if(newCostToNeighbour < neighbour.getgCost() || !openSet.contains(neighbour)) {
          neighbour.setgCost(newCostToNeighbour);
          neighbour.sethCost(graph.getDistance(neighbour, targetNode));
          neighbour.setParent(currentNode);
          
          if(!openSet.contains(neighbour))
            openSet.add(neighbour);
        }
      }
    }
    return openSet.toList();
  }
  
  static List<Node> retracePath(Node startNode, Node endNode) {
    List<Node> path = new ArrayList<>();
    Node currentNode = endNode;
    
    while(currentNode != startNode) {
      path.add(0, currentNode);
      currentNode = currentNode.getParent();
    }
    path.add(0, currentNode);
    
    return path;
  }
}
