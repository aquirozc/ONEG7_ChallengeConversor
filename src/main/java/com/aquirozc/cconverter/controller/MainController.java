package com.aquirozc.cconverter.controller;

import java.util.HashMap;
import java.util.Map;

import com.aquirozc.cconverter.data.RecordDatabase;
import com.aquirozc.cconverter.exchange.ERKnowledgeBase;
import com.aquirozc.cconverter.net.ERApiClient;
import com.aquirozc.cconverter.preferences.SharedPreferences;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController{

    private Map<ControllerDirectory, IControllerFXML> map = new HashMap<>();
    private ControllerDirectory lastScene = null;
    private Stage stage;

    public MainController(Stage stage){

        this.stage = stage;
        this.stage.setResizable(false);
        this.stage.show();

        map.put(ControllerDirectory.SETUP_CONTROLLER, new SetupController(this));
        map.put(ControllerDirectory.CONVERTER_CONTROLLER, new ConverterController(this));
        map.put(ControllerDirectory.RECORD_CONTROLLER, new RecordController(this));

        try {
            RecordDatabase.init();
            SharedPreferences.init();
            ERKnowledgeBase base = ERApiClient.fetchApiData(SharedPreferences.getApiKey());
            map.get(ControllerDirectory.CONVERTER_CONTROLLER).begin(base);
        } catch (Exception e) {
            map.get(ControllerDirectory.SETUP_CONTROLLER).begin();
        }
        
    }

    public IControllerFXML getController(ControllerDirectory k){
        return map.get(k);
    }

    public void updateScene(ControllerDirectory k){

        if (lastScene == null){
            lastScene = k;
            this.stage.setScene(new Scene(map.get(k).getParent(), 1000,600));
            return;
        }

        try {
			
			// Crear un contenedor que contendrá ambas escenas
			Group rootGroup = new Group();
			Parent oldroot = stage.getScene().getRoot();

			// Añadir la escena antigua al contenedor
			rootGroup.getChildren().add(oldroot);

			// Añadir la nueva escena al contenedor (fuera de la vista)
			map.get(k).getParent().setTranslateX(stage.getWidth());
			rootGroup.getChildren().add(map.get(k).getParent());

			// Crear una nueva transición de deslizamiento desde el lado derecho
			TranslateTransition transition = new TranslateTransition();
			transition.setDuration(Duration.millis(500));
			transition.setFromX(stage.getWidth());
			transition.setToX(0);

			// Aplicar la transición al nodo de la nueva escena
			transition.setNode(map.get(k).getParent());
			transition.play();

			// Aplicar el contenedor con ambas escenas a la ventana
			stage.getScene().setRoot(rootGroup);
			
			PauseTransition	pause = new PauseTransition(Duration.millis(650));
			pause.setOnFinished(e -> rootGroup.getChildren().remove(oldroot));
			pause.play();
			
		} catch (Exception e) {}
    }

    public void updateSceneBasic(ControllerDirectory k) {
		stage.getScene().setRoot(map.get(k).getParent());
	}
 
}
