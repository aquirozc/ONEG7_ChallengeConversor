package com.aquirozc.cconverter.net;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import com.aquirozc.cconverter.exchange.ERKnowledgeBase;
import com.google.gson.Gson;

public class ERApiClient {

    private static final String URL_TEMPLATE = "https://v6.exchangerate-api.com/v6/%s/latest/USD";

    private static HttpClient client = HttpClient.newHttpClient();
    private static Gson gson = new Gson();

    public static ERKnowledgeBase fetchApiData(String key) throws Exception{

        HttpRequest request = HttpRequest.newBuilder(URI.create(String.format(URL_TEMPLATE, key)))
                                    .GET()
                                    .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        if (response.statusCode() != 200) throw new Exception();

        return deserializeJSON(response.body());

    }

    public static ERKnowledgeBase deserializeJSON(String json){
        return gson.fromJson(json, ERKnowledgeBase.class);
    }
    
}
