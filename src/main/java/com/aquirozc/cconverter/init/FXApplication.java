package com.aquirozc.cconverter.init;

import com.aquirozc.cconverter.controller.MainController;

import javafx.application.Application;
import javafx.stage.Stage;

public class FXApplication extends Application{
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        new MainController(primaryStage);
    }

}
