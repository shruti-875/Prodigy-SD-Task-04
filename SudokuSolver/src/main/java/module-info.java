module org.example.sudokusolver {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.sudokusolver to javafx.fxml;
    exports org.example.sudokusolver;
}