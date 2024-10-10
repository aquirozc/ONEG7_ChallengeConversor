package com.aquirozc.cconverter.data;

import java.util.Date;
import java.io.Serializable;

import com.aquirozc.cconverter.exchange.Country;

public record ExchangeRecord(

        Country from,
        Country to,
        float input,
        float output,
        long time,
        long definition

) implements Serializable{
    
        @Override
        public final String toString() {
            return "De: " + from.toString() +
                        "\nA: " + to.toString() +
                        "\nCantidad: " +  input +
                        "\nResultado: " + output +
                        "\nFecha: " +  new Date(time) +
                        "\nDefinición: " + new Date(definition);
        }

}