package graph;

import java.util.Set;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EdgeGUI {
  Set<Node> nodes;
  Line line;
  double[] xPoints, yPoints;
  int nPoints;
  boolean isActive;

  public EdgeGUI(Node a, Node b, Line line, double[] xPoints, double[] yPoints) {
    this.nodes = Set.of(a, b);
    this.line = line;
    this.xPoints = xPoints;
    this.yPoints = yPoints;
    this.nPoints = xPoints.length;
  }

  public void setActive(boolean active) {
    this.isActive = active;
  }

  public boolean isEqual(Set<Node> other) {
    return nodes.equals(other);
  }
  
  @SuppressWarnings("exports")
  public void draw(GraphicsContext gc) {
    Color color = isActive ? Color.DEEPPINK : line.getColor();
    gc.setStroke(color);
    gc.setLineWidth(4);
    gc.strokePolyline(xPoints, yPoints, nPoints);
    if (isActive) {
      color = Color.HOTPINK;
      gc.setStroke(color);
      gc.setLineWidth(2);
      gc.strokePolyline(xPoints, yPoints, nPoints);
    }
  }

}
