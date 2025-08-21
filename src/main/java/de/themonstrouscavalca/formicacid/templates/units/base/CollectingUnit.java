package de.themonstrouscavalca.formicacid.templates.units.base;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Map;

public abstract class CollectingUnit implements IAmAUnit{
    protected final Map<String, IAmAUnit> unitMap;

    public CollectingUnit(Map<String, IAmAUnit> unitMap){
        this.unitMap = unitMap;
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
