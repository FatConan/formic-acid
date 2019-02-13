package de.themonstrouscavalca.formicacid.validators.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class IntermediateValidateOptional<T> extends AbstractOptional<T>{
    private List<String> errors;

    public IntermediateValidateOptional(){
        this(null);
    }

    public IntermediateValidateOptional(T initialValue){
        this.errors = new ArrayList<>();
        this.valid = true;
        this.validatedValue = initialValue;
    }

    public List<String> getErrors(){
        return errors;
    }

    public void setErrors(List<String> errors){
        this.errors = errors;
    }

    public void addError(String error){
        this.errors.add(error);
    }

    public void addErrors(List<String> errors){
        this.errors.addAll(errors);
    }

    public void merge(IntermediateValidateOptional<T> validated){
        this.setValid(this.isValid() && validated.isValid());
        this.setPresentInJson(this.isPresentInJson() || validated.isPresentInJson());
        this.addErrors(validated.getErrors());
        this.validatedValue = validated.getValidatedValue();
    }
}
