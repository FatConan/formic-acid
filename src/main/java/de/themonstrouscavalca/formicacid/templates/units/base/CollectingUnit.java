package de.themonstrouscavalca.formicacid.templates.units.base;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CollectingUnit implements IAmAUnit{
    protected final Map<String, IAmAUnit> unitMap;

    public CollectingUnit(Map<String, IAmAUnit> unitMap){
        this.unitMap = unitMap;
    }

    public Map<String, String[]> transpose(Map<String, String[]> data){
        Map<String, String[]> transposed = new HashMap<>();
        for(Map.Entry<String, IAmAUnit> unitEntry : this.unitMap.entrySet()){
            String fieldName = unitEntry.getKey();
            Pattern fieldPattern = Pattern.compile("(" + fieldName + ")_([0-9]+)");
            if(data.containsKey(fieldName)){
                transposed.put(fieldName, data.get(fieldName));
            }else{
                //TODO - Is this a sensible solution???
                List<String> entries = new ArrayList<>();
                for(String key : data.keySet()){
                    Matcher m = fieldPattern.matcher(key);
                    if(m.matches()){
                        entries.add(m.group(2));
                    }
                }

                if(!entries.isEmpty()){
                    String[] finalArr = new String[entries.size()];
                    transposed.put(fieldName, entries.toArray(finalArr));
                }
            }
        }
        return transposed;
    }

    @Override
    public void handleData(Map<String, String[]> data){
        for(Map.Entry<String, IAmAUnit> unitEntry : this.unitMap.entrySet()){
            String fieldName = unitEntry.getKey();
            IAmAUnit unit = unitEntry.getValue();
            if(data.containsKey(fieldName)){
                unit.data(data.get(fieldName));
            }else{
                unit.data((String) null);
            }
        }
    }

    @Override
    public void handleData(JsonNode data){
        for(Map.Entry<String, IAmAUnit> unitEntry : this.unitMap.entrySet()){
            String fieldName = unitEntry.getKey();
            IAmAUnit unit = unitEntry.getValue();
            if(data.has(fieldName)){
                unit.data(data.get(unitEntry.getKey()).asText());
            }else{
                unit.data((String) null);
            }
        }
    }

    @Override
    public void handleErrors(JsonNode errors){
        //Pass the errors down to the units and set any directly
        for(Map.Entry<String, IAmAUnit> entry : unitMap.entrySet()){
            String fieldName = entry.getKey();
            IAmAUnit unit = entry.getValue();
            if(errors.has(fieldName)){
                unit.errors(errors.get(fieldName).asText());
            }
            unit.handleErrors(errors);
        }
    }
}
