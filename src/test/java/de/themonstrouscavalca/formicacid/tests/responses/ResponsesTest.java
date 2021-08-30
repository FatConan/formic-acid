package de.themonstrouscavalca.formicacid.tests.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.themonstrouscavalca.formicacid.abs.AbstractMarshaller;
import de.themonstrouscavalca.formicacid.difference.DifferenceProcessor;
import org.junit.Test;

import static org.junit.Assert.*;


public class ResponsesTest{
    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void emptyResponse(){
        JsonNode emptyResponse = AbstractMarshaller.emptyErrorResponse();
        JsonNode reference;
        try{
            reference = mapper.readTree("{\"errors\":{}, \"global_errors\": []}");
            assertTrue(DifferenceProcessor.match(emptyResponse, reference));
        }catch(JsonProcessingException e){
            fail("Unable to parse reference json string");
        }
    }

    @Test
    public void simpleGlobalErrors(){
        JsonNode simpleGlobal = AbstractMarshaller.simpleGlobalErrorResponse("ERROR");
        JsonNode reference;
        try{
            reference = mapper.readTree("{\"errors\":{}, \"global_errors\": [\"ERROR\"]}");
            assertTrue(DifferenceProcessor.match(simpleGlobal, reference));
        }catch(JsonProcessingException e){
            fail("Unable to parse reference json string");
        }
    }




}
