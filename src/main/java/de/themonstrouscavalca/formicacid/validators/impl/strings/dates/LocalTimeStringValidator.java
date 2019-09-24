package de.themonstrouscavalca.formicacid.validators.impl.strings.dates;

import de.themonstrouscavalca.formicacid.formatters.DateFormatters;
import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.time.LocalTime;
import java.util.Optional;

public class LocalTimeStringValidator extends AbstractValidator<String> implements IValidate<String>{
    private final String DEFAULT_ERROR_MSG = "The provided value is not parsable as a time";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    public LocalTimeStringValidator(){
        super(null);
    }

    @Override
    public IntermediateValidateOptional<String> getValidatedValue(String value){
        IntermediateValidateOptional<String> intermediate = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            try {
                LocalTime.parse(value, DateFormatters.API_TIME_FORMAT.getFormatter());
            }catch (Exception e){
                intermediate.setValid(false);
                intermediate.addError(this.getErrorMessage());
            }
        }
        return intermediate;
    }
}

