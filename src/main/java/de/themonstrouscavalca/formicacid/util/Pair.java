package de.themonstrouscavalca.formicacid.util;

/**
 * Implementation for a simple Pair generic - taken from https://stackoverflow.com/a/61821499
 * as a simple replacement for the javafx version previously being used (which is Oracle specific).
 * @param <T> Key
 * @param <U> Value
 */
public class Pair<T,U> {
    @FunctionalInterface
    public interface KeyFunction<T, V>{
        T call(V entity);
    }

    @FunctionalInterface
    public interface ValueFunction<U, V>{
        U call(V entity);
    }

    public static <V, T, U> Pair<T, U> from(V entity, KeyFunction<T, V> key, ValueFunction<U, V> value){
        return new Pair<>(key.call(entity), value.call(entity));
    }

    private final T key;
    private final U value;

    public Pair(T key, U value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return this.key;
    }

    public U getValue() {
        return this.value;
    }
}
