module JAAB.Apps {
    requires javafx.controls;
    requires javafx.fxml;
    requires poi.ooxml;

    opens JAAB.Apps to javafx.fxml;
    exports JAAB.Apps;
}