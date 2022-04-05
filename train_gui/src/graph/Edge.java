package graph;

public class Edge {
  Node begin;
  int distance;
  Node end;
  Line line; 
  
  public Edge(Node begin, int distance, Node end, Line line) {
    this.begin = begin;
    this.distance = distance;
    this.end = end;
    this.line = line;
  }
  
  @Override
  public String toString() {
    return "{ " + end + "  - " + distance + "km ->  " + begin + " }";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((begin == null) ? 0 : begin.hashCode());
    result = prime * result + distance;
    result = prime * result + ((end == null) ? 0 : end.hashCode());
    result = prime * result + ((line == null) ? 0 : line.hashCode());
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
    Edge other = (Edge) obj;
    if (begin != other.begin)
      return false;
    if (distance != other.distance)
      return false;
    if (end != other.end)
      return false;
    if (line != other.line)
      return false;
    return true;
  }
}
