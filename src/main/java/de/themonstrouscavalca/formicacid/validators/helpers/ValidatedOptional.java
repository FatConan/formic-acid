package de.themonstrouscavalca.formicacid.validators.helpers;

public class ValidatedOptional<T>{
    private static final ValidatedOptional<?> EMPTY = new ValidatedOptional<>(null, true, false, new String[]{});

    private final IntermediateValidateOptional<T> finalIntermediate;
    private final boolean valid;
    private final boolean presentInJson;
    private final T validatedValue;
    private final String[] errors;

    public static<T> ValidatedOptional<T> empty(){
        @SuppressWarnings("unchecked")
        ValidatedOptional<T> t = (ValidatedOptional<T>) EMPTY;
        return t;
    }

    public ValidatedOptional(T value){
        this(value, true, true, new String[]{});
    }

    public ValidatedOptional(T value, boolean presentInJson){
        this(value, true, presentInJson, new String[]{});
    }

    public ValidatedOptional(T validatedValue, boolean valid, boolean presentInJson, String[] errors){
        this.finalIntermediate = null;
        this.valid = valid;
        this.presentInJson = presentInJson;
        this.validatedValue = validatedValue;
        this.errors = errors;
    }

    public ValidatedOptional(IntermediateValidateOptional<T> finalIntermediate){
        this.finalIntermediate = finalIntermediate;
        this.valid = finalIntermediate.isValid();
        this.presentInJson = finalIntermediate.isPresentInJson();
        this.validatedValue = finalIntermediate.getValidatedValue();

        String[] errors = new String[finalIntermediate.getErrors().size()];
        finalIntermediate.getErrors().toArray(errors);
        this.errors = errors;
    }

    public T get(){
        return this.validatedValue;
    }

    public boolean isPresent(){
        return this.presentInJson;
    }

    public boolean isEmpty(){
        return this.validatedValue == null;
    }

    public boolean isValid(){
        return valid;
    }

    public T getValidatedValue(){
        return validatedValue;
    }

    public String[] getErrors(){
        return this.errors;
    }

    public IntermediateValidateOptional<T> getFinaleIntermediate(){
        return this.finalIntermediate;
    }
}

