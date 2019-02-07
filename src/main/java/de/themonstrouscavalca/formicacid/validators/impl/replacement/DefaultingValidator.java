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
    public IntermediateValidateOptional<T> getValidatedValue(Optional<T> value){
        IntermediateValidateOptional<T> intermediate = new IntermediateValidateOptional<>();
        if(value.isPresent()){
           intermediate.setValidatedValue(value);
        }else{
            intermediate.setValidatedValue(Optional.of(this.replacement));
        }
        return intermediate;
    }
}
