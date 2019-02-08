package de.themonstrouscavalca.formicacid.validators.impl.complex;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.util.Collection;
import java.util.Optional;

public class CollectionLengthRangeValidator <T extends Collection> extends AbstractValidator<T> implements IValidate<T>{
    private final String DEFAULT_ERROR_MSG = "You must select a number of entries between %d and %d";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    private final long min;
    private final long max;


    public CollectionLengthRangeValidator(long min, long max){
        super(null);
        this.min = min;
        this.max = max;
    }

    public CollectionLengthRangeValidator(long min, long max, String outOfBoundsMessage){
        super(outOfBoundsMessage);
        this.min = min;
        this.max = max;
    }

    private void outOfBounds(IntermediateValidateOptional<T> inter){
        inter.setValid(false);
        inter.addError(this.getParameterisedErrorMessage(min, max));
    }

    @Override
    public IntermediateValidateOptional<T> getValidatedValue(T value){
        IntermediateValidateOptional<T> inter = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            int size = value.size();
            if(size < min || size > max){
                outOfBounds(inter);
            }
        }
        return inter;
    }
}
