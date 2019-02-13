package de.themonstrouscavalca.formicacid.validators.impl.complex;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.util.Collection;

public class CollectionMaxLengthValidator<T extends Collection> extends AbstractValidator<T> implements IValidate<T>{
    private final String DEFAULT_ERROR_MSG = "You may select no more that %d entries";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    private final long max;


    public CollectionMaxLengthValidator(long max){
        super(null);
        this.max = max;
    }

    public CollectionMaxLengthValidator(long max, String outOfBoundsMessage){
        super(outOfBoundsMessage);
        this.max = max;
    }

    private void outOfBounds(IntermediateValidateOptional<T> inter){
        inter.setValid(false);
        inter.addError(this.getParameterisedErrorMessage(max));
    }

    @Override
    public IntermediateValidateOptional<T> getValidatedValue(T value){
        IntermediateValidateOptional<T> inter = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            int size = value.size();
            if(size > max){
                outOfBounds(inter);
            }
        }
        return inter;
    }
}
