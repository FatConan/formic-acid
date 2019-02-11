package de.themonstrouscavalca.formicacid.helpers;

public class PresentValue<T>{
    private final boolean presentInJson;
    private final boolean parsable;
    private final T extractedValue;

    public PresentValue(boolean present, boolean parsable, T extractedValue){
        this.extractedValue = extractedValue;
        this.parsable = parsable;
        this.presentInJson = present;
    }

    public T get(){
        return this.extractedValue;
    }

    public boolean isPresentInJson(){
        return this.presentInJson;
    }

    public boolean isParsable(){
        return this.parsable;
    }
}
