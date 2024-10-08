package com.aquirozc.cconverter.controller;

import com.aquirozc.cconverter.exchange.Country;
import com.aquirozc.cconverter.exchange.ERKnowledgeBase;
import com.aquirozc.cconverter.exchange.OnlineConversor;
import com.aquirozc.cconverter.exchange.SampleData;
import com.google.gson.Gson;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

@SuppressWarnings("unchecked")
public class MainController implements IControllerFXML {

    private Parent parent = this.getActivity("Main.fxml");

    private TextField ammountTF = (TextField) parent.lookup("#ammount_tf");
    private ComboBox<Country> fromCB = (ComboBox<Country>) parent.lookup("#from_cb");
    private ComboBox<Country> toCB = (ComboBox<Country>) parent.lookup("#to_cb");
    private Button convertBTN = (Button) parent.lookup("#convert_btn");

    private OnlineConversor conversor = new OnlineConversor(new Gson().fromJson(SampleData.SAMPLE_JSON, ERKnowledgeBase.class));

    public MainController (Stage stage){
        Scene scene = new Scene(parent);
		stage.setMinWidth(800);
		stage.setMinHeight(575);
		stage.setWidth(900);
		stage.setHeight(600);
		stage.setTitle("Conversor de Monedas (Alejandro Quiroz Carmona)");
		stage.setScene(scene);
		stage.show();

        fromCB.getItems().addAll(Country.values());
        toCB.getItems().addAll(Country.values());

        convertBTN.setOnMouseClicked(this::convertAmmount);
    }

    private void convertAmmount(MouseEvent e){

        Country from = fromCB.getSelectionModel().getSelectedItem();
        Country to = toCB.getSelectionModel().getSelectedItem();

        float x = Float.parseFloat(ammountTF.getText());
        float y = conversor.convert(from, to, x);

        new Alert(javafx.scene.control.Alert.AlertType.INFORMATION, String.format("%f %s = %f %s", x, from.ISO4217Code, y, to.ISO4217Code)).showAndWait();

    }
    
}
