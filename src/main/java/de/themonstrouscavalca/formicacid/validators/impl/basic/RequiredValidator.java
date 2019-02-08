package de.themonstrouscavalca.formicacid.validators.impl.basic;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.util.Optional;

public class RequiredValidator<T> extends AbstractValidator<T> implements IValidate<T>{
    private final String DEFAULT_ERROR_MSG = "This is a required field";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    public RequiredValidator(){
        super(null);
    }

    public RequiredValidator(String errorMessage){
        super(errorMessage);
    }

    @Override
    public IntermediateValidateOptional<T> getValidatedValue(T value){
        IntermediateValidateOptional<T> intermediate = new IntermediateValidateOptional<>(value);
        if(!this.isPresent(value)){
            intermediate.setValid(false);
            intermediate.addError(this.getErrorMessage());
        }
        return intermediate;
    }
}
