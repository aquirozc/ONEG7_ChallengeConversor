package com.aquirozc.cconverter.test;

import com.aquirozc.cconverter.exchange.Country;
import com.aquirozc.cconverter.exchange.ERKnowledgeBase;
import com.aquirozc.cconverter.exchange.OnlineConversor;
import com.aquirozc.cconverter.exchange.SampleData;
import com.google.gson.Gson;

public class OfflineConversionTest {

    public static String test(){

        ERKnowledgeBase base = new Gson().fromJson(SampleData.SAMPLE_JSON,ERKnowledgeBase.class);
        OnlineConversor conversor = new OnlineConversor(base);

        float x = 500;
        float y = conversor.convert(Country.ESTADOS_UNIDOS, Country.MEXICO, x);

        return String.format("%f %s = %f %s", x, Country.ESTADOS_UNIDOS.ISO4217Code, y, Country.MEXICO.ISO4217Code);

    }
    
}
