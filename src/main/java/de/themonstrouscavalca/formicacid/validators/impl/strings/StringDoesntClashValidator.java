package de.themonstrouscavalca.formicacid.validators.impl.strings;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.util.Arrays;
import java.util.Collection;

public class StringDoesntClashValidator extends AbstractValidator<String> implements IValidate<String>{
    private final String DEFAULT_ERROR_MSG = "The provided value clashes with an existing value";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    private final Collection<String> existing;

    public StringDoesntClashValidator(String msg, String ... possibleValues){
        super(msg);
        this.existing = Arrays.asList(possibleValues);
    }

    @Override
    public IntermediateValidateOptional<String> getValidatedValue(String value){
        IntermediateValidateOptional<String> intermediate = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            if(existing.contains(value)){
                intermediate.setValid(false);
                intermediate.addError(this.getErrorMessage());
            }
        }
        return intermediate;
    }
}
