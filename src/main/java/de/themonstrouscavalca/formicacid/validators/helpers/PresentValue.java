package de.themonstrouscavalca.formicacid.validators.helpers;

public class PresentValue<T>{
    private final boolean presentInJson;
    private final T extractedValue;

    public PresentValue(boolean present, T extractedValue){
        this.extractedValue = extractedValue;
        this.presentInJson = present;
    }

    public T get(){
        return this.extractedValue;
    }

    public boolean isPresentInJson(){
        return this.presentInJson;
    }
}
