package de.themonstrouscavalca.formicacid.helpers.responses;

import com.fasterxml.jackson.databind.JsonNode;

public class ResponsePackage{
    private final ResponseType responseType;
    private final JsonNode response;

    public ResponsePackage(ResponseType responseType, JsonNode response){
        this.responseType = responseType;
        this.response = response;
    }

    public ResponseType getResponseType(){
        return responseType;
    }

    public JsonNode getResponse(){
        return response;
    }

    public boolean isOK(){
        return ResponseType.OK.equals(responseType);
    }

    public boolean isError(){
        return ResponseType.ERROR.equals(responseType);
    }
}
