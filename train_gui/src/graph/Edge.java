package graph;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Edge {
  Node begin;
  int distance;
  Node end;
  Line line;
  boolean isActive;
//  double[] xPoints, yPoints;
//  int nPoints;

  public Edge(Node begin, int distance, Node end, Line line) {
    this.begin = begin;
    this.distance = distance;
    this.end = end;
    this.line = line;
//    this.nPoints = xPoints.length;
  }

  public void setActive(boolean isActive) {
    this.isActive = isActive;
  }

  public void draw(GraphicsContext gc) {
    Color color = isActive ? Color.ORANGE : this.getLine().getColor();
    gc.setStroke(color);
    gc.setLineWidth(4);
    //    gc.strokePolyline(xPoints, yPoints, nPoints);
  }

  @Override
  public String toString() {
    return "{ " + end + "  - " + getDistance() + "km ->  " + getBegin() + " }";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getBegin() == null) ? 0 : getBegin().hashCode());
    result = prime * result + getDistance();
    result = prime * result + ((end == null) ? 0 : end.hashCode());
    result = prime * result + ((getLine() == null) ? 0 : getLine().hashCode());
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
    if (getBegin() != other.getBegin())
      return false;
    if (getDistance() != other.getDistance())
      return false;
    if (end != other.end)
      return false;
    if (getLine() != other.getLine())
      return false;
    return true;
  }

  public Node getBegin() {
    return begin;
  }

  public Line getLine() {
    return line;
  }

  public int getDistance() {
    return distance;
  }
}
