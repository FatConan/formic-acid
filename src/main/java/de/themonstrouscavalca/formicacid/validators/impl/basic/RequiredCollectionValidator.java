package de.themonstrouscavalca.formicacid.validators.impl.basic;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.util.Collection;
import java.util.Optional;

public class RequiredCollectionValidator<T extends Collection> extends AbstractValidator<T> implements IValidate<T>{
    protected final String DEFAULT_ERROR_MESSAGE = "This is a required field";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MESSAGE;
    }

    public RequiredCollectionValidator(){
        super(null);
    }

    public RequiredCollectionValidator(String errorMessage){
        super(errorMessage);
    }

    @Override
    public IntermediateValidateOptional<T> getValidatedValue(T value){
        IntermediateValidateOptional<T> inter = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value) && !inter.isEmpty()){
            inter.setValid(true);
        }else{
            inter.addError(this.getErrorMessage());
            inter.setValid(false);
        }
        return inter;
    }
}
