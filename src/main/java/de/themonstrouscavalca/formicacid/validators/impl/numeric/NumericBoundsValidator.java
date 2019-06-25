package de.themonstrouscavalca.formicacid.validators.impl.numeric;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;
import org.apache.commons.lang3.NotImplementedException;

import java.util.Optional;

public class NumericBoundsValidator<T> extends AbstractValidator<T> implements IValidate<T>{
    private final String DEFAULT_ERROR_MSG = "You must enter a value between %d and %d";
    private final String DEFAULT_FLOAT_ERROR_MSG = "You must enter a value between %f and %f";

    @Override
    protected String defaultError(){
        if(min instanceof Double || max instanceof Double){
            return DEFAULT_FLOAT_ERROR_MSG;
        }
        return DEFAULT_ERROR_MSG;
    }


    private final T min;
    private final T max;

    public NumericBoundsValidator(T min, T max){
        super(null);
        this.min = min;
        this.max = max;
    }

    public NumericBoundsValidator(T min, T max, String outOfBoundsMessage){
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
            if(value instanceof Double){
                if((Double) value < (Double) min || (Double) value > (Double) max){
                    outOfBounds(inter);
                }
            }else if(value instanceof Long){
                if((Long) value < (Long) min || (Long) value > (Long) max){
                    outOfBounds(inter);
                }
            }else if(value instanceof Integer){
                if((Integer) value < (Integer) min || (Integer) value > (Integer) max){
                    outOfBounds(inter);
                }
            }else{
                throw new NotImplementedException("This validator is only applicable to Doubles, Integers and Longs");
            }
        }
        return inter;
    }
}

