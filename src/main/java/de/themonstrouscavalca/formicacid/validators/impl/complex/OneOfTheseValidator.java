package de.themonstrouscavalca.formicacid.validators.impl.complex;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.util.Collection;
import java.util.Optional;

public class OneOfTheseValidator<T> extends AbstractValidator<T> implements IValidate<T>{
    private final Collection<T> these;
    private final String DEFAULT_ERROR_MSG = "This is not a permitted value";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    public OneOfTheseValidator(Collection<T> these){
        super(null);
        this.these = these;
    }

    public OneOfTheseValidator(Collection<T> these, String errorMessage){
        super(errorMessage);
        this.these = these;
    }

    @Override
    public IntermediateValidateOptional<T> getValidatedValue(Optional<T> value){
        IntermediateValidateOptional<T> intermediate = new IntermediateValidateOptional<>(value);
        if(!value.isPresent() || !this.these.contains(value.get())){
            intermediate.setValid(false);
            intermediate.addError(this.getErrorMessage());
        }
        return intermediate;
    }
}