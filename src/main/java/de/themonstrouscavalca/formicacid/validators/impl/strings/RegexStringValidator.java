package de.themonstrouscavalca.formicacid.validators.impl.strings;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexStringValidator extends AbstractValidator<String> implements IValidate<String>{
    private final String DEFAULT_ERROR_MSG = "The provided value is not in the correct format";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    private final Pattern pattern;

    public RegexStringValidator(Pattern pattern){
        super(null);
        this.pattern = pattern;
    }

    public RegexStringValidator(Pattern pattern, String errorMessage){
        super(errorMessage);
        this.pattern = pattern;
    }

    @Override
    public IntermediateValidateOptional<String> getValidatedValue(String value){
        IntermediateValidateOptional<String> intermediate = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            Matcher m = pattern.matcher(value);
            if(!m.matches()){
                intermediate.setValid(false);
                intermediate.addError(this.getErrorMessage());
            }
        }
        return intermediate;
    }
}