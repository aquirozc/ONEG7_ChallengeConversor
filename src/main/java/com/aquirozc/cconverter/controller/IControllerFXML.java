package com.aquirozc.cconverter.controller;

import com.aquirozc.cconverter.init.CurrencyConverter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public interface IControllerFXML {

	default Parent getActivity(String n) {

		Parent parent = null;

		try {
			parent = new FXMLLoader(CurrencyConverter.class.getClassLoader().getResource(n)).load();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return parent;
	}

}
