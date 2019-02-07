package de.themonstrouscavalca.formicacid.validators.impl.strings;


import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Optional;


public class EmailStringValidator extends AbstractValidator<String> implements IValidate<String>{
    private final String DEFAULT_ERROR_MSG = "This is not a valid e-mail address";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    public EmailStringValidator(){
        super(null);
    }

    public EmailStringValidator(String errorMessage){
        super(errorMessage);
    }

    @Override
    public IntermediateValidateOptional<String> getValidatedValue(Optional<String> value){
        IntermediateValidateOptional<String> intermediate = new IntermediateValidateOptional<>(value);
        if(value.isPresent() && !value.get().isEmpty()){
            try {
                InternetAddress emailAddr = new InternetAddress(value.get());
                emailAddr.validate();
            } catch (AddressException ex) {
                intermediate.setValid(false);
                intermediate.addError(this.getErrorMessage());
            }
        }
        return intermediate;
    }
}

