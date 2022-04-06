module train_gui {
	requires javafx.controls;
  requires transitive javafx.fxml;
  
  exports application.controller to javafx.fxml; 
  
  opens application to javafx.graphics, javafx.fxml;
  opens application.controller to javafx.fxml;
}
