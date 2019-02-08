package de.themonstrouscavalca.formicacid.validators.impl.strings.dates;

import de.themonstrouscavalca.formicacid.formatters.DateFormatters;
import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.time.LocalDateTime;
import java.util.Optional;

public class LocalDateTimeStringValidator extends AbstractValidator<String> implements IValidate<String>{
    private final String DEFAULT_ERROR_MSG = "The provided value is not parsable as a datetime";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    public LocalDateTimeStringValidator(){
        super(null);
    }

    @Override
    public IntermediateValidateOptional<String> getValidatedValue(String value){
        IntermediateValidateOptional<String> intermediate = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            LocalDateTime datetime = null;
            try {
                datetime = LocalDateTime.parse(value, DateFormatters.API_DATE_TIME_FORMAT);
            }catch (Exception e){
                intermediate.setValid(false);
                intermediate.addError(this.getErrorMessage());
            }
        }
        return intermediate;
    }
}

