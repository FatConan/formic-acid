package de.themonstrouscavalca.formicacid.validators.impl.numeric;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Optional;

public class NonZeroValidator<T> extends AbstractValidator<T> implements IValidate<T>{
    private final String DEFAULT_ERROR_MSG = "You must enter a non zero value";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    private void zeroError(IntermediateValidateOptional<T> inter){
        inter.setValid(false);
        inter.addError(this.getErrorMessage());
    }

    @Override
    public IntermediateValidateOptional<T> getValidatedValue(T value){
        IntermediateValidateOptional<T> inter = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            if(value instanceof Double){
                if((Double) value == 0){
                    zeroError(inter);
                }
            }else if(value instanceof Long){
                if((Long) value == 0){
                    zeroError(inter);
                }
            }else if(value instanceof Integer){
                if((Integer) value == 0){
                    zeroError(inter);
                }
            }else{
                throw new NotImplementedException("This validator is only applicable to Doubles, Integers and Longs");
            }
        }
        return inter;
    }
}
