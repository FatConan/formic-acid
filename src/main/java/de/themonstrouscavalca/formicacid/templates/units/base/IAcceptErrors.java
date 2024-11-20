package de.themonstrouscavalca.formicacid.templates.units.base;

import com.fasterxml.jackson.databind.JsonNode;

public interface IAcceptErrors{
    default void errors(String errors){
        //No-op by default
    }
    default void handleErrors(JsonNode error){
        //No-op by default
    }
}
