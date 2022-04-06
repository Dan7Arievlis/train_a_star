package graph;

public class Node implements Comparable<Node> {
  public static final Node 
      E1  = new Node("E1"),
      E2  = new Node("E2"),
      E3  = new Node("E3"),
      E4  = new Node("E4"),
      E5  = new Node("E5"),
      E6  = new Node("E6"),
      E7  = new Node("E7"),
      E8  = new Node("E8"),
      E9  = new Node("E9"),
      E10 = new Node("E10"),
      E11 = new Node("E11"),
      E12 = new Node("E12"),
      E13 = new Node("E13"),
      E14 = new Node("E14");      
  
  String name;
  int gCost;
  int hCost;
  boolean walkable = true;
  Node parent;
  int heapIndex;

  public Node(String name) {
    this.name = name;
  }

  public int getHeapIndex() {
    return heapIndex;
  }

  public void setHeapIndex(int heapIndex) {
    this.heapIndex = heapIndex;
  }

  public String getName() {
    return name;
  }
  
  public int getfCost() {
    return gCost + hCost;
  }

  public int getgCost() {
    return gCost;
  }

  public void setgCost(int gCost) {
    this.gCost = gCost;
  }

  public int gethCost() {
    return hCost;
  }

  public void sethCost(int hCost) {
    this.hCost = hCost;
  }

  public Node getParent() {
    return parent;
  }

  public void setParent(Node parent) {
    this.parent = parent;
  }

  public boolean isWalkable() {
    return this.walkable;
  }

  @Override
  public int compareTo(Node other) {
    int compare = Integer.compare(this.getfCost(), other.getfCost());
    if (compare == 0)
      compare = Integer.compare(this.gethCost(), other.gethCost());

    return -compare;
  }

  @Override
  public String toString() {
    return this.name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + heapIndex;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Node other = (Node) obj;
    if (heapIndex != other.heapIndex)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }
}
