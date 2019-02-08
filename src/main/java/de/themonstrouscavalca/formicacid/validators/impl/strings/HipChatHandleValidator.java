package de.themonstrouscavalca.formicacid.validators.impl.strings;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.util.regex.Pattern;

public class HipChatHandleValidator extends AbstractValidator<String> implements IValidate<String>{
    private final String DEFAULT_ERROR_MSG = "This must start with an @ and may not contain any whitespace";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    private final Pattern test = Pattern.compile("^@[^\\s]+$");

    @Override
    public IntermediateValidateOptional<String> getValidatedValue(String value){
        IntermediateValidateOptional<String> inter = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            if(!test.matcher(value).matches()){
                inter.setValid(false);
                inter.addError(this.getErrorMessage());
            }
        }
        return inter;
    }
}
