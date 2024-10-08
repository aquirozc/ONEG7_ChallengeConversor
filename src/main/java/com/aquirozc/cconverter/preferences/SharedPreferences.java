package com.aquirozc.cconverter.preferences;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class SharedPreferences {

    private final static String DEFAULT_PROPS_LOCATION = "./Prefs.xml";

    private final static Properties props = new Properties();

    public static void init() throws IOException{
        props.loadFromXML(new FileInputStream(DEFAULT_PROPS_LOCATION));
    }

    public static String getApiKey(){
        return props.getProperty("API_KEY");
    }

    public static void saveApiKey(String key) throws IOException{
        props.setProperty("API_KEY", key);
        props.storeToXML(new FileOutputStream(DEFAULT_PROPS_LOCATION), "");
    }
    
}
