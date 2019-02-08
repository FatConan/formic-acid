package de.themonstrouscavalca.formicacid.validators.impl.strings.dates;

import de.themonstrouscavalca.formicacid.formatters.DateFormatters;
import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.time.LocalDate;
import java.util.Optional;

public class LocalDateStringValidator extends AbstractValidator<String> implements IValidate<String>{
    private final String DEFAULT_ERROR_MSG = "The provided value is not parsable as a date";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    public LocalDateStringValidator(){
        super(null);
    }

    @Override
    public IntermediateValidateOptional<String> getValidatedValue(String value){
        IntermediateValidateOptional<String> intermediate = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            try {
                LocalDate.parse(value, DateFormatters.API_DATE_FORMAT);
            }catch (Exception e){
                intermediate.setValid(false);
                intermediate.addError(this.getErrorMessage());
            }
        }
        return intermediate;
    }
}

