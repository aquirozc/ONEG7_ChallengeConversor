package com.aquirozc.cconverter.init;

import com.aquirozc.cconverter.controller.MainController;

import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class FXApplication extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Font.loadFont(CurrencyConverter.class.getClassLoader().getResourceAsStream("Khula-Light.ttf"),20);
        new MainController(primaryStage);
    }

}
