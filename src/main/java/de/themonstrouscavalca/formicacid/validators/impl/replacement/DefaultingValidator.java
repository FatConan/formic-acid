package de.themonstrouscavalca.formicacid.validators.impl.replacement;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;

import java.util.Optional;

public class DefaultingValidator<T> implements IValidate<T>{
    private final T replacement;

    public DefaultingValidator(T replacement){
        this.replacement = replacement;
    }

    @Override
    public IntermediateValidateOptional<T> getValidatedValue(T value){
        IntermediateValidateOptional<T> intermediate = new IntermediateValidateOptional<>();
        if(value != null){
           intermediate.setValidatedValue(value);
           intermediate.setPresentInJson(true);
        }else{
            intermediate.setValidatedValue(this.replacement);
            intermediate.setPresentInJson(true);
        }
        return intermediate;
    }
}
