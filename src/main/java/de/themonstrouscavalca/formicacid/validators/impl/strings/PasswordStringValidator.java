package de.themonstrouscavalca.formicacid.validators.impl.strings;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.util.Optional;

public class PasswordStringValidator extends AbstractValidator<String> implements IValidate<String>{
    private final String DEFAULT_ERROR_MSG = "This does not conform to our password policy";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    public PasswordStringValidator(){
        super(null);
    }

    public PasswordStringValidator(String errorMessage){
        super(errorMessage);
    }

    @Override
    public IntermediateValidateOptional<String> getValidatedValue(String value){
        IntermediateValidateOptional<String> intermediate = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            //TODO  defined a decent password policy, until then just pass through whatever's returned
        }
        return intermediate;
    }
}

