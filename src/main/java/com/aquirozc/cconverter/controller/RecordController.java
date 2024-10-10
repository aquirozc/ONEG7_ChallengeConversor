package com.aquirozc.cconverter.controller;

import java.util.List;
import java.util.Collections;

import com.aquirozc.cconverter.data.ExchangeRecord;
import com.aquirozc.cconverter.init.CurrencyConverter;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class RecordController implements IControllerFXML {

    private Parent parent = getActivity("Record.fxml");
    private BorderPane pane = (BorderPane) parent.lookup("#target");
    private Button returnBTN = (Button) parent.lookup("#return_btn");
	private Label label = new Label("No hay vehículos para mostrar");
    private VBox row;
    private ScrollPane table;
    private MainController controller;

    public RecordController(MainController controller){
        this.controller = controller;
        returnBTN.setOnMouseClicked(e -> controller.updateScene(ControllerDirectory.CONVERTER_CONTROLLER));
    }

    private void animateButtons(VBox vbox) {

		 Duration duration = Duration.seconds(0.5);

		 for (int i = 0; i < vbox.getChildren().size(); i++) {
	            Button button = (Button) vbox.getChildren().get(i);

	            FadeTransition fadeTransition = new FadeTransition(duration, button);
	            fadeTransition.setToValue(1);
	            fadeTransition.setDelay(duration.multiply(i));
	            fadeTransition.play();

	            TranslateTransition transition = new TranslateTransition(duration, button);
	            transition.setFromY(680); 
	            transition.setToY(0);
	            transition.setInterpolator(Interpolator.EASE_BOTH);
	            transition.setDelay(duration.multiply(i));
	            transition.play();
	        }
		 
    }

    @Override
    public void begin() {
        controller.updateScene(ControllerDirectory.RECORD_CONTROLLER);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void begin(Object key){

        List<ExchangeRecord> list = (List<ExchangeRecord>) key;
		
		if (list.isEmpty()) {
			table = null;
			pane.setCenter(label);
		}else {
		
			row = new VBox();
			row.setSpacing(20);
			
			table = new ScrollPane(row);
			table.setPadding(new Insets(33,200,33,200));
			table.setFitToWidth(true);

            Collections.reverse(list);
			
			for(ExchangeRecord record : list){

                Label label = new Label(record.toString());

                ImageView img = new ImageView(CurrencyConverter.class.getClassLoader().getResource("money_refund.png").toString());
                img.setFitHeight(130);
                img.setFitWidth(130);

                HBox box = new HBox();
				box.setSpacing(20);
				box.setAlignment(Pos.CENTER_LEFT);
                box.getChildren().addAll(img, label);


                Button boton = new Button();
				boton.getStyleClass().addAll("primary-button");
				boton.setMaxWidth(Double.MAX_VALUE);
				boton.setOpacity(0);
                boton.setGraphic(box);
				
				row.getChildren().add(boton);
            }

            animateButtons(row);
            pane.setCenter(table);

        }

        begin();

    }

    @Override
    public Parent getParent() {
        return parent;
    }
    
}
