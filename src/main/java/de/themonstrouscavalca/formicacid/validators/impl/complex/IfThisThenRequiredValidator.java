package de.themonstrouscavalca.formicacid.validators.impl.complex;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

public class IfThisThenRequiredValidator<T> extends AbstractValidator<T> implements IValidate<T>{
    private final Boolean ifThis;
    private final String DEFAULT_ERROR_MSG = "This is a required field";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    public IfThisThenRequiredValidator(Boolean ifThis){
        super(null);
        this.ifThis = ifThis;
    }

    public IfThisThenRequiredValidator(Boolean ifThis, String errorMessage){
        super(errorMessage);
        this.ifThis = ifThis;
    }

    @Override
    public IntermediateValidateOptional<T> getValidatedValue(T value){
        IntermediateValidateOptional<T> intermediate = new IntermediateValidateOptional<>(value);
        if(this.ifThis){
            if(!this.isPresent(value)){
                intermediate.setValid(false);
                intermediate.addError(this.getErrorMessage());
            }
        }
        return intermediate;
    }
}

