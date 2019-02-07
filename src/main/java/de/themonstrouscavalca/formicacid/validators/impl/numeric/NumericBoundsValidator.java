package de.themonstrouscavalca.formicacid.validators.impl.numeric;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Optional;

public class NumericBoundsValidator<T> extends AbstractValidator<T> implements IValidate<T>{
    private final String DEFAULT_ERROR_MSG = "You must enter a value between %d and %d";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    private final long min;
    private final long max;


    public NumericBoundsValidator(long min, long max){
        super(null);
        this.min = min;
        this.max = max;
    }

    public NumericBoundsValidator(long min, long max, String outOfBoundsMessage){
        super(outOfBoundsMessage);
        this.min = min;
        this.max = max;
    }

    private void outOfBounds(IntermediateValidateOptional<T> inter){
        inter.setValid(false);
        inter.addError(this.getParameterisedErrorMessage(min, max));
    }

    @Override
    public IntermediateValidateOptional<T> getValidatedValue(Optional<T> value){
        IntermediateValidateOptional<T> inter = new IntermediateValidateOptional<>(value);
        if(value.isPresent()){
            T val = value.get();
            if(val instanceof Double){
                if((Double) val < min || (Double) val > max){
                    outOfBounds(inter);
                }
            }else if(val instanceof Long){
                if((Long) val < min || (Long) val > max){
                    outOfBounds(inter);
                }
            }else if(val instanceof Integer){
                if((Integer) val < min || (Integer) val > max){
                    outOfBounds(inter);
                }
            }else{
                throw new NotImplementedException("This validator is only applicable to Doubles, Integers and Longs");
            }
        }
        return inter;
    }
}

