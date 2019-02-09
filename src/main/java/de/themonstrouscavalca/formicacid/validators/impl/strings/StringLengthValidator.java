package de.themonstrouscavalca.formicacid.validators.impl.strings;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;

import java.util.Optional;

public class StringLengthValidator implements IValidate<String>{
    private static final String DEFAULT_ERROR_MSG = "You must enter between %d and %d characters";
    private static final String DEFAULT_MIN_ONLY_MSG = "You must enter at least %d characters";
    private static final String DEFAULT_MAX_ONLY_MSG = "You must enter at most %d characters";

    private final Optional<Integer> minLength;
    private final Optional<Integer> maxLength;
    private final Optional<String> defaultErrorMsg;
    private final Optional<String> minOnlyErrorMsg;
    private final Optional<String> maxOnlyErrorMsg;

    public StringLengthValidator(Integer min, Integer max){
        this.minLength = Optional.ofNullable(min);
        this.maxLength = Optional.ofNullable(max);
        this.defaultErrorMsg = Optional.empty();
        this.minOnlyErrorMsg = Optional.empty();
        this.maxOnlyErrorMsg = Optional.empty();
    }
    public StringLengthValidator(Integer min, Integer max, String errorMsg, String minOnly, String maxOnly){
        this.minLength = Optional.ofNullable(min);
        this.maxLength = Optional.ofNullable(max);
        this.defaultErrorMsg = Optional.ofNullable(errorMsg);
        this.minOnlyErrorMsg = Optional.ofNullable(minOnly);
        this.maxOnlyErrorMsg = Optional.ofNullable(maxOnly);
    }

    /* If it's a required field then set to min -> max otherwise allow zero length */
    public StringLengthValidator(Boolean isRequired, Integer min, Integer max){
        if(isRequired != null && isRequired){
            this.minLength = Optional.ofNullable(min);
        }else{
            this.minLength = Optional.of(0);
        }
        this.maxLength = Optional.ofNullable(max);
        this.defaultErrorMsg = Optional.empty();
        this.minOnlyErrorMsg = Optional.empty();
        this.maxOnlyErrorMsg = Optional.empty();
    }

    public StringLengthValidator(Boolean isRequired, Integer min, Integer max, String errorMsg, String minOnly, String maxOnly){
        if(isRequired != null && isRequired){
            this.minLength = Optional.ofNullable(min);
        }else{
            this.minLength = Optional.of(0);
        }
        this.maxLength = Optional.ofNullable(max);
        this.defaultErrorMsg = Optional.ofNullable(errorMsg);
        this.minOnlyErrorMsg = Optional.ofNullable(minOnly);
        this.maxOnlyErrorMsg = Optional.ofNullable(maxOnly);
    }

    private String getAppropriateErrorMessage(){
        if(this.maxLength.isPresent() && this.minLength.isPresent()){
            String err = this.defaultErrorMsg.isPresent() ? this.defaultErrorMsg.get() : DEFAULT_ERROR_MSG;
            return String.format(err, this.minLength.get(), this.maxLength.get());
        }else if(this.maxLength.isPresent()){
            String err = this.maxOnlyErrorMsg.isPresent() ? this.maxOnlyErrorMsg.get() : DEFAULT_MAX_ONLY_MSG;
            return String.format(err, this.maxLength.get());
        }else if(this.minLength.isPresent()){
            String err = this.minOnlyErrorMsg.isPresent() ? this.minOnlyErrorMsg.get() : DEFAULT_MIN_ONLY_MSG;
            return String.format(err, this.minLength.get());
        }
        String err = this.defaultErrorMsg.isPresent() ? this.defaultErrorMsg.get() : DEFAULT_ERROR_MSG;
        return String.format(err, this.minLength.get(), this.maxLength.get());
    }

    private boolean isPresent(String value){
        return value != null;
    }

    @Override
    public IntermediateValidateOptional<String> getValidatedValue(String value){
        IntermediateValidateOptional<String> inter = new IntermediateValidateOptional<>(value);

        if(this.isPresent(value)){
            if(value.isEmpty()){
                if(minLength.isPresent() && minLength.get() > 0){
                    inter.setValid(false);
                    inter.addError(this.getAppropriateErrorMessage());
                }
            }else{
                if(minLength.isPresent() && value.length() < minLength.get()){
                    inter.setValid(false);
                    inter.addError(this.getAppropriateErrorMessage());
                }else if(maxLength.isPresent() && value.length() > maxLength.get()){
                    inter.setValid(false);
                    inter.addError(this.getAppropriateErrorMessage());
                }
            }
        }

        return inter;
    }
}
