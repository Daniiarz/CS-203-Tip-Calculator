package sample;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Controller {
    public static final NumberFormat currency = NumberFormat.getCurrencyInstance();
    public static final NumberFormat percent = NumberFormat.getPercentInstance();

    @FXML private Label priceLabel;
    @FXML private Label tipPercentLabel;
    @FXML private Label tipLabel;
    @FXML private Label totalLabel;
    @FXML private TextField priceTextField;
    @FXML private TextField tipTextField;
    @FXML private TextField totalTextField;
    @FXML private Button calcBtn;
    @FXML private Slider tipPercentSlider;

    @FXML
    void initialize(){
        tipPercentSlider.setValue(15.0);

        tipPercentSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                String percent_value = percent.format(tipPercentSlider.getValue()/100);
                tipPercentLabel.setText(percent_value);
            }
        });
    }

    @FXML
    void CalcBtnFnc(ActionEvent event){
        try{
            BigDecimal price = new BigDecimal(priceTextField.getText());
            BigDecimal percent = new BigDecimal(tipPercentSlider.getValue());

            BigDecimal percent2 = percent.divide(new BigDecimal(100));
            BigDecimal tip = price.multiply(percent2);
            BigDecimal total = price.add(tip);

            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        }
        catch (Exception e){
            priceTextField.setText("Enter number!");
            priceTextField.selectAll();
            priceTextField.requestFocus();
        }

    }

}
