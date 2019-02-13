package de.themonstrouscavalca.formicacid.validators.helpers;

import java.util.Collection;

public class AbstractOptional<T>{
    protected boolean valid;
    protected boolean presentInJson;
    protected T validatedValue;

    public boolean isValid(){
        return valid;
    }

    public boolean isEmpty(){
        if(this.validatedValue == null){
            return true;
        }

        if(this.validatedValue instanceof Collection){
            return ((Collection) this.validatedValue).isEmpty();
        }

        return false;
    }

    public void setValid(boolean valid){
        this.valid = valid;
    }

    public T getValidatedValue(){
        return validatedValue;
    }

    public void setValidatedValue(T validatedValue){
        this.validatedValue = validatedValue;
    }

    public boolean isPresentInJson(){
        return presentInJson;
    }

    public void setPresentInJson(boolean presentInJson){
        this.presentInJson = presentInJson;
    }
}
