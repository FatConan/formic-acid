package de.themonstrouscavalca.formicacid.templates.units.base;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public interface IAcceptData{
    default void data(String data){
        //No-op by default
    }
    default void data(String[] data){
        //No-op by default
    }
    default void handleData(Map<String, String[]> data){
        //No-op by default
    }
    default void handleData(JsonNode data){
        //No-op by default
    }
}
