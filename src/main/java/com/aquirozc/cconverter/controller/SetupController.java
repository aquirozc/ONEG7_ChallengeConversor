package com.aquirozc.cconverter.controller;

import com.aquirozc.cconverter.exchange.ERKnowledgeBase;
import com.aquirozc.cconverter.exchange.SampleData;
import com.aquirozc.cconverter.net.ERApiClient;
import com.aquirozc.cconverter.preferences.SharedPreferences;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SetupController implements IControllerFXML {

    private Parent parent = getActivity("Setup.fxml");
    private Button useFetchDataBTN = (Button) parent.lookup("#use_fetchdata_btn");
    private Button useSampleDataBTN = (Button) parent.lookup("#use_sampledata_btn");
    private TextField keyTF = (TextField) parent.lookup("#key_tf");

    private MainController controller;

    public SetupController(MainController controller){
        this.controller = controller;

        this.useFetchDataBTN.setOnMouseClicked(this::useFetchData);
        this.useSampleDataBTN.setOnMouseClicked(this::useSampleData);
    }

    @Override
    public void begin() {
        controller.updateScene(ControllerDirectory.SETUP_CONTROLLER);
    }

    @Override
    public void begin(Object key) {
        this.begin();
    }

    @Override
    public Parent getParent() {
        return parent;
    }

    private void useFetchData(MouseEvent e){

        try {

            String key = keyTF.getText();
            ERKnowledgeBase base = ERApiClient.fetchApiData(key);

            try {
                SharedPreferences.saveApiKey(key);
            } catch (Exception error) {
                
            }

            controller.getController(ControllerDirectory.CONVERTER_CONTROLLER).begin(base);

        } catch (Exception error) {
            return;
        }

    }

    private void useSampleData(MouseEvent e){
        ERKnowledgeBase base = ERApiClient.deserializeJSON(SampleData.SAMPLE_JSON);
        controller.getController(ControllerDirectory.CONVERTER_CONTROLLER).begin(base);
    }
    
}
