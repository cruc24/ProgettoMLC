package com.Project.beans;


import java.util.List;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class JsonConverter {
    
    private final Gson gson;
    
    public JsonConverter() {
        
        gson = new GsonBuilder().create();
    }

    public String convertToJson(List<Film> films) {
        JsonArray jarray = gson.toJsonTree(films).getAsJsonArray();
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("films", jarray);
        return jsonObject.toString();
    }
}