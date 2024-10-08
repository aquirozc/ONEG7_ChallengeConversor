package com.aquirozc.cconverter.exchange;

import java.util.Map;

public class OnlineConversor {

    private Map<String,Float> rates;

    public OnlineConversor(ERKnowledgeBase knowledgeBase){
        this.rates = knowledgeBase.rates();
    }

    public float convert(Country from, Country to, float ammount){
        return (ammount / rates.get(from.ISO4217Code)) * rates.get(to.ISO4217Code);
    }

    public void updateRates(ERKnowledgeBase knowledgeBase){
        this.rates = knowledgeBase.rates();
    }

    
}
