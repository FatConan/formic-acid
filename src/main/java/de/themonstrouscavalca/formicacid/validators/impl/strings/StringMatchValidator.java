package de.themonstrouscavalca.formicacid.validators.impl.strings;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.util.Optional;

public class StringMatchValidator extends AbstractValidator<String> implements IValidate<String>{
    private final String DEFAULT_ERROR_MSG = "The provided values do not match";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    private final String toMatch;

    public StringMatchValidator(String toMatch){
        super(null);
        this.toMatch = toMatch;
    }

    public StringMatchValidator(String toMatch, String errorMessage){
        super(errorMessage);
        this.toMatch = toMatch;
    }

    @Override
    public IntermediateValidateOptional<String> getValidatedValue(String value){
        IntermediateValidateOptional<String> intermediate = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            if(!value.equals(toMatch)){
                intermediate.setValid(false);
                intermediate.addError(this.getErrorMessage());
            }
        }
        return intermediate;
    }
}

