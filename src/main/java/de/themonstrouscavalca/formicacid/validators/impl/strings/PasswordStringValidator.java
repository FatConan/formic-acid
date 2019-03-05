package de.themonstrouscavalca.formicacid.validators.impl.strings;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordStringValidator extends AbstractValidator<String> implements IValidate<String>{
    private final String DEFAULT_ERROR_MSG = "Passwords must contain a mixture of upper and lowercase characters as well as at least one number";
    private final List<Pattern> patterns = Arrays.asList(
            Pattern.compile("([A-Z]+)"),
            Pattern.compile("([a-z)]+)"),
            Pattern.compile("([0-9]+)")
    );

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
            boolean sufficient = true;
            for(Pattern test: this.patterns){
                Matcher matcher = test.matcher(value);
                sufficient = sufficient && matcher.find();
            }
            if(!sufficient){
                intermediate.setValid(false);
                intermediate.addError(this.getErrorMessage());
            }
        }
        return intermediate;
    }
}

