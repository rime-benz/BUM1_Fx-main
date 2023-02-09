module eus.ehu.bum1_fx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens eus.ehu.bum1_fx to javafx.fxml;
    exports eus.ehu.bum1_fx;
    exports clui;
    opens clui to javafx.fxml;
    exports Gui;
    opens Gui to javafx.fxml;
    exports busniss_logic;
    opens busniss_logic to javafx.fxml;
}
