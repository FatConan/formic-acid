package de.themonstrouscavalca.formicacid.validators.impl.complex;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.util.Optional;

public class OneOrTheOtherValidator<T> extends AbstractValidator<T> implements IValidate<T>{
    private final T theOther;
    private final String DEFAULT_ERROR_MSG = "This is a required field";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    public OneOrTheOtherValidator(T theOther){
        super(null);
        this.theOther = theOther;
    }

    public OneOrTheOtherValidator(T theOther, String errorMessage){
        super(errorMessage);
        this.theOther = theOther;
    }

    @Override
    public IntermediateValidateOptional<T> getValidatedValue(T value){
        IntermediateValidateOptional<T> intermediate = new IntermediateValidateOptional<>(value);
        if(!this.isPresent(value) && !this.isPresent(theOther)){
            intermediate.setValid(false);
            intermediate.addError(this.getErrorMessage());
        }
        return intermediate;
    }
}

