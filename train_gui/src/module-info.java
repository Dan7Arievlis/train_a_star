module train_gui {
	requires javafx.controls;
  requires transitive javafx.fxml;
  requires java.desktop;
  
  exports application.controller to javafx.fxml;
  exports graph to javafx.fxml;
  
  opens application to javafx.graphics, javafx.fxml;
  opens application.controller to javafx.fxml;
}
