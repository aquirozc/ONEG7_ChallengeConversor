package com.aquirozc.cconverter.controller;

import com.aquirozc.cconverter.exchange.Country;
import com.aquirozc.cconverter.exchange.ERKnowledgeBase;
import com.aquirozc.cconverter.exchange.OnlineConversor;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

@SuppressWarnings("unchecked")
public class ConverterController implements IControllerFXML{

    private Parent parent = this.getActivity("Main.fxml");

    private TextField ammountTF = (TextField) parent.lookup("#ammount_tf");
    private ComboBox<Country> fromCB = (ComboBox<Country>) parent.lookup("#from_cb");
    private ComboBox<Country> toCB = (ComboBox<Country>) parent.lookup("#to_cb");
    private Button convertBTN = (Button) parent.lookup("#convert_btn");

    private OnlineConversor conversor;
    private MainController controller;

    public ConverterController (MainController controller){
        this.controller = controller;

        fromCB.getItems().addAll(Country.values());
        toCB.getItems().addAll(Country.values());

        convertBTN.setOnMouseClicked(this::convertAmmount);
    }

    @Override
    public void begin() {}

    @Override
    public void begin(Object key) {
        conversor = new OnlineConversor((ERKnowledgeBase) key);
        controller.updateScene(ControllerDirectory.CONVERTER_CONTROLLER);
        controller.updateSceneBasic(ControllerDirectory.CONVERTER_CONTROLLER);
    }

    @Override
    public Parent getParent() {
        return parent;
    }

    private void convertAmmount(MouseEvent e){

        Country from = fromCB.getSelectionModel().getSelectedItem();
        Country to = toCB.getSelectionModel().getSelectedItem();

        float x = Float.parseFloat(ammountTF.getText());
        float y = conversor.convert(from, to, x);

        new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, String.format("%f %s = %f %s", x, from.ISO4217Code, y, to.ISO4217Code)).showAndWait();
    }
    
}
