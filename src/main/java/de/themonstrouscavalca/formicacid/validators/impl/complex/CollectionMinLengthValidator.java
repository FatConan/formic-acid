package de.themonstrouscavalca.formicacid.validators.impl.complex;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.util.Collection;

public class CollectionMinLengthValidator<T extends Collection> extends AbstractValidator<T> implements IValidate<T>{
    private final String DEFAULT_ERROR_MSG = "You must select at least %d entries";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    private final long min;


    public CollectionMinLengthValidator(long min){
        super(null);
        this.min = min;
    }

    public CollectionMinLengthValidator(long min, String outOfBoundsMessage){
        super(outOfBoundsMessage);
        this.min = min;
    }

    private void outOfBounds(IntermediateValidateOptional<T> inter){
        inter.setValid(false);
        inter.addError(this.getParameterisedErrorMessage(min));
    }

    @Override
    public IntermediateValidateOptional<T> getValidatedValue(T value){
        IntermediateValidateOptional<T> inter = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            int size = value.size();
            if(size < min){
                outOfBounds(inter);
            }
        }
        return inter;
    }
}
