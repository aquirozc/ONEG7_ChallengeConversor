package com.aquirozc.cconverter.controller;

import com.aquirozc.cconverter.data.ExchangeRecord;
import com.aquirozc.cconverter.data.RecordDatabase;
import com.aquirozc.cconverter.exchange.Country;
import com.aquirozc.cconverter.exchange.ERKnowledgeBase;
import com.aquirozc.cconverter.exchange.OnlineConversor;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

@SuppressWarnings("unchecked")
public class ConverterController implements IControllerFXML{

    private Parent parent = this.getActivity("Converter.fxml");

    private TextField ammountTF = (TextField) parent.lookup("#ammount_tf");
    private ComboBox<Country> fromCB = (ComboBox<Country>) parent.lookup("#from_cb");
    private ComboBox<Country> toCB = (ComboBox<Country>) parent.lookup("#to_cb");
    private Button convertBTN = (Button) parent.lookup("#convert_btn");
    private Button launchRecordBTN = (Button) parent.lookup("#launch_record_btn");

    private OnlineConversor conversor;
    private MainController controller;

    public ConverterController (MainController controller){
        this.controller = controller;

        fromCB.getItems().addAll(Country.values());
        fromCB.getSelectionModel().select(Country.MEXICO);
        toCB.getItems().addAll(Country.values());
        toCB.getSelectionModel().select(Country.ESTADOS_UNIDOS);

        launchRecordBTN.setOnMouseClicked(e -> controller.getController(ControllerDirectory.RECORD_CONTROLLER).begin(RecordDatabase.getList()));
        ammountTF.textProperty().addListener(this::validateInput);
        convertBTN.setOnMouseClicked(this::convertAmmount);
    }

    @Override
    public void begin() {
        controller.updateScene(ControllerDirectory.CONVERTER_CONTROLLER);
    }

    @Override
    public void begin(Object key) {
        conversor = new OnlineConversor((ERKnowledgeBase) key);
        begin();
    }

    @Override
    public Parent getParent() {
        return parent;
    }

    private void convertAmmount(MouseEvent e){

        try {
            Country from = fromCB.getSelectionModel().getSelectedItem();
            Country to = toCB.getSelectionModel().getSelectedItem();

            float x = Float.parseFloat(ammountTF.getText());
            float y = conversor.convert(from, to, x);

            RecordDatabase.saveRecord(new ExchangeRecord(from, to, x, y, System.currentTimeMillis(), conversor.getDate()));

            new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, String.format("%f %s = %f %s", x, from.ISO4217Code, y, to.ISO4217Code)).showAndWait();
        } catch (Exception error) {
            new Alert(AlertType.ERROR,"Por favor rellena bien los campos").showAndWait();
        }

    }


    private void validateInput(ObservableValue<? extends String> observable, String oldValue, String newValue){

        if (newValue.matches("\\d*(\\.\\d*)?")) {
            return;
        }

        ((StringProperty) observable).set(oldValue);

    }
    
}
