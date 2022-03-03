package de.themonstrouscavalca.formicacid.difference;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * A quick and dirty set of tools for doing top level comparisons between JSON data sets of the sort that is frequently
 * returned from form validators. It allows a quick view of top level additions/deletions and alterations and can be toggled
 * to only highlight those fields that without revealing the associated values.
 */
public class DifferenceProcessor{
    private static final Logger logger = LoggerFactory.getLogger(DifferenceProcessor.class);
    protected static final ObjectMapper mapper = new ObjectMapper();

    private static JsonNode parse(String jsonText){
        if(jsonText != null){
            try{
                return mapper.readTree(jsonText);
            }catch(JsonProcessingException e){
                return mapper.createObjectNode();
            }
        }
        return null;
    }

    public static final String HIDDEN_VALUE = "<HIDDEN VALUE>";
    public static final String EMPTY_VALUE =  "<EMPTY VALUE>";
    public static final String IN = "in";
    public static final String OUT = "out";
    public static final String ALTERED = "altered";
    public static final String ALTERED_FORMAT = "%s ⇒ %s";
    public static final String KEYED_VALUE_FORMAT = "[%s]:%s";

    public static JsonNode differences(String from, String to){
        return differences(from != null ? parse(from) : null, to != null ? parse(to) : null);
    }

    public static JsonNode differences(String from, String to, boolean redactValues){
        return differences(from != null ? parse(from) : null, to != null ? parse(to) : null, redactValues);
    }

    public static String prettyText(String jsonText){
        try{
            Object json = mapper.readValue(jsonText, Object.class);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json);
        }catch(IOException e){
            return "";
        }
    }

    public static String prettyText(JsonNode node){
        try{
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        }catch(IOException e){
            return "";
        }
    }

    public static String toText(JsonNode node){
        if(node == null){
            return null;
        }else if(node.isObject()){
            Collection<String> s = new ArrayList<>();
            for(Iterator<String> it = node.fieldNames(); it.hasNext(); ){
                String field = it.next();
                JsonNode n = node.get(field);
                s.add(String.format(KEYED_VALUE_FORMAT, field, toText(n)));
            }
            return String.join(",", s);
        }else if(node.isArray()){
            Collection<String> s = new ArrayList<>();
            for(JsonNode n : node){
                s.add(toText(n));
            }
            return String.join(",", s);
        }else{
            return node.asText();
        }
    }

    public static JsonNode differences(JsonNode from, JsonNode to, boolean redactValues){
        ObjectNode output = mapper.createObjectNode();
        ObjectNode in = output.putObject(IN);
        ObjectNode out = output.putObject(OUT);
        ObjectNode altered = output.putObject(ALTERED);

        if(from == null && to != null){
            updateObjectNode(out, to);
        }else if(from != null && to == null){
            updateObjectNode(in, from);
        }else if(from != null){
            if(from.isObject()){
                for(Iterator<String> it = from.fieldNames(); it.hasNext(); ){
                    String field = it.next();
                    String fromString = toText(from.get(field));
                    String toString =   toText(to.get(field));

                    if(toString == null){
                        if(redactValues){
                            out.put(field, EMPTY_VALUE);
                        }else{
                            out.put(field, fromString);
                        }
                    }else if(!fromString.equals(toString)){
                        if(redactValues){
                            altered.put(field, HIDDEN_VALUE);
                        }else{
                            altered.put(field, String.format(ALTERED_FORMAT, fromString, toString));
                        }
                    }
                }
            }

            if(to.isObject()){
                for(Iterator<String> it = to.fieldNames(); it.hasNext(); ){
                    String field = it.next();
                    String fromString = toText(from.get(field));
                    String toString = toText(to.get(field));
                    if(fromString == null){
                        if(redactValues){
                            in.put(field, HIDDEN_VALUE);
                        }else{
                            in.put(field, toString);
                        }
                    }
                }
            }
        }
        return output;
    }

    public static JsonNode differences(JsonNode from, JsonNode to){
        return differences(from, to, false);
    }

    public static boolean match(JsonNode from, JsonNode to){
        JsonNode leftDiff = differences(from, to);
        return (leftDiff.get(IN).isEmpty() && leftDiff.get(OUT).isEmpty() && leftDiff.get(ALTERED).isEmpty());
    }

    public static void updateObjectNode(ObjectNode object, JsonNode node){
        for(Iterator<String> it = node.fieldNames(); it.hasNext(); ){
            String field = it.next();
            String value = toText(node.get(field));
            object.put(field, value);
        }
    }
}

