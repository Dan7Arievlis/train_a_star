package graph;

import javafx.scene.paint.Color;

public enum Line {
  RED{
    public Color getColor(){
      return Color.RED;
    }
  }, 
  GREEN{
    public Color getColor(){
      return Color.GREEN;
    }
  },
  BLUE{
    public Color getColor(){
      return Color.BLUE;
    }
  },
  YELLOW{
    public Color getColor(){
      return Color.YELLOW;
    }
  };

  @SuppressWarnings("exports")
  public abstract Color getColor();
}
