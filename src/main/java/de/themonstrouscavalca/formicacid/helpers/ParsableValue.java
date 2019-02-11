package de.themonstrouscavalca.formicacid.helpers;

public class ParsableValue<T>{
    private static final ParsableValue<?> NULL = new ParsableValue<>(true, null);
    private static final ParsableValue<?> INVALID = new ParsableValue<>(false, null);

    private final boolean parsable;
    private final T extractedValue;

    public ParsableValue(boolean parsable, T extractedValue){
        this.extractedValue = extractedValue;
        this.parsable = parsable;
    }

    public T get(){
        return this.extractedValue;
    }

    public boolean isParsable(){
        return this.parsable;
    }

    public boolean isNull(){
        return extractedValue == null;
    }

    public static <T> ParsableValue<T> of(boolean parsable, T value){
        return new ParsableValue<>(parsable, value);
    }

    public static<T> ParsableValue<T> empty(){
        @SuppressWarnings("unchecked")
        ParsableValue<T> t = (ParsableValue<T>) NULL;
        return t;
    }

    public static<T> ParsableValue<T> invalid(){
        @SuppressWarnings("unchecked")
        ParsableValue<T> t = (ParsableValue<T>) INVALID;
        return t;
    }
}
