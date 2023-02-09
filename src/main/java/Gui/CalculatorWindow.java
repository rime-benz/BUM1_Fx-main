package Gui;

import busniss_logic.BarcenaysCalculator;
import busniss_logic.ExchangeCalculator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatorWindow extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CalculatorWindow.class.getResource("calculator.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 305);
        stage.setTitle("Currency Exchange Calculator");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
