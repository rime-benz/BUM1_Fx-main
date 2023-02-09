package eus.ehu.bum1_fx;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import busniss_logic.*;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class CalculatorController {

        @FXML
        private TextField amountTextField;

        @FXML
        private ComboBox<String> fromComboBox;

        @FXML
        private Label result;

        @FXML
        private ComboBox<String> toComboBox;
        public  ExchangeCalculator businesslogic = new BarcenaysCalculator();

        @FXML
        void initialize() {
            // initialize toComboBox
            fromComboBox.setItems(FXCollections.observableArrayList(Currency.longNames()));

            // initialize fromComboBox
            toComboBox.setItems(FXCollections.observableArrayList(Currency.longNames()));

            result.setBackground(new Background(
                    new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));


        }

        @FXML
        void onClick(ActionEvent event) {
            try {
                double origAmount = Double.parseDouble(amountTextField.getText());

					/* Invalid numbers will be trapped in the catch block. But
					   non-positive numbers will also be discarded */
                if (origAmount <= 0) throw new NumberFormatException();
                String origCurrency = (String) fromComboBox.getValue();
                origCurrency = origCurrency.substring(0, 3);
                String endCurrency = (String) toComboBox.getValue();
                endCurrency = endCurrency.substring(0, 3);

					/* If both chosen currencies are equal the online converter
					   won't provide a result */
                if (origCurrency.equals(endCurrency)) {
                    result.setText("Please select different currencies");
                } else {
                   //// ForexOperator operator = new ForexOperator(origCurrency,
                            //origAmount, endCurrency);
                    try {
                        double destAmount = businesslogic.getChangeValue(origCurrency,
                                origAmount, endCurrency);
                       // CommissionCalculator calculator = new CommissionCalculator(destAmount,
                               // endCurrency);
                        destAmount -= businesslogic.calculateComission(destAmount,endCurrency);
                        NumberFormat twoDecimal = NumberFormat.getNumberInstance(Locale.US);
                        twoDecimal.setMaximumFractionDigits(2);
                        twoDecimal.setRoundingMode(RoundingMode.FLOOR);
                        result.setText(twoDecimal.format(destAmount));

                    } catch (Exception e1) {
                        result.setText("Conversion could not be done");
                    }
                }
            } catch (NumberFormatException e2) {
                result.setText("Please introduce a valid amount");
            }
        }


}
