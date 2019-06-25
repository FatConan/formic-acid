package de.themonstrouscavalca.formicacid.validators.impl.strings;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import org.apache.commons.validator.routines.UrlValidator;

public class URLStringValidator extends AbstractValidator<String> implements IValidate<String>{
    private final String DEFAULT_ERROR_MSG = "This is not a valid URL";
    private final String[] SCHEMES = {"http","https"};
    private final UrlValidator URL_VALIDATOR = new UrlValidator(SCHEMES, UrlValidator.ALLOW_LOCAL_URLS);

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    public URLStringValidator(){
        super(null);
    }

    public URLStringValidator(String errorMessage){
        super(errorMessage);
    }

    @Override
    public IntermediateValidateOptional<String> getValidatedValue(String value){
        IntermediateValidateOptional<String> intermediate = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            if (!URL_VALIDATOR.isValid(value)) {
                intermediate.setValid(false);
                intermediate.addError(this.getErrorMessage());
            }
        }
        return intermediate;
    }
}
