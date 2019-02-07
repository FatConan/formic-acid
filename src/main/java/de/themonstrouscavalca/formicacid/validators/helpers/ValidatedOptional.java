package de.themonstrouscavalca.formicacid.validators.helpers;

import java.util.Optional;

public class ValidatedOptional<T>{
    private static final ValidatedOptional<?> EMPTY = new ValidatedOptional<>(Optional.empty(), true, new String[]{});

    private final IntermediateValidateOptional finalIntermediate;
    private final boolean valid;
    private final Optional<T> validatedValue;
    private final String[] errors;

    public static<T> ValidatedOptional<T> empty() {
        @SuppressWarnings("unchecked")
        ValidatedOptional<T> t = (ValidatedOptional<T>) EMPTY;
        return t;
    }

    public ValidatedOptional(T value){
        this(Optional.of(value), true, new String[]{});
    }

    public ValidatedOptional(Optional<T> validatedValue, boolean valid, String[] errors){
        this.finalIntermediate = null;
        this.valid = valid;
        this.validatedValue = validatedValue;
        this.errors = errors;
    }

    public ValidatedOptional(IntermediateValidateOptional<T> finalIntermediate){
        this.finalIntermediate = finalIntermediate;
        this.valid = finalIntermediate.isValid();
        this.validatedValue = finalIntermediate.getValidatedValue();

        String[] errors = new String[finalIntermediate.getErrors().size()];
        finalIntermediate.getErrors().toArray(errors);
        this.errors = errors;
    }

    public T get(){
        return validatedValue.get();
    }

    public boolean isPresent(){
        return validatedValue.isPresent();
    }

    public boolean isValid(){
        return valid;
    }

    public Optional<T> getValidatedValue(){
        return validatedValue;
    }

    public String[] getErrors(){
        return this.errors;
    }

    public Optional<IntermediateValidateOptional<T>> getFinaleIntermediate(){
        return Optional.ofNullable(finalIntermediate);
    }
}

