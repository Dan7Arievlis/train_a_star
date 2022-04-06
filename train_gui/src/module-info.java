module train_gui {
	requires javafx.controls;
  requires transitive javafx.fxml;
	
  opens application to javafx.graphics, javafx.fxml;
}
