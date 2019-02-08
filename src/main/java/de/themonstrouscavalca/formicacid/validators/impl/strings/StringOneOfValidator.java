package de.themonstrouscavalca.formicacid.validators.impl.strings;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class StringOneOfValidator extends AbstractValidator<String> implements IValidate<String>{
    private final String DEFAULT_ERROR_MSG = "The provided value is not one of the permitted values";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    private final Collection<String> toMatch;

    public StringOneOfValidator(String msg, String ... possibleValues){
        super(msg);
        this.toMatch = Arrays.asList(possibleValues);
    }

    @Override
    public IntermediateValidateOptional<String> getValidatedValue(String value){
        IntermediateValidateOptional<String> intermediate = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            if(!toMatch.contains(value)){
                intermediate.setValid(false);
                intermediate.addError(this.getErrorMessage());
            }
        }
        return intermediate;
    }
}

