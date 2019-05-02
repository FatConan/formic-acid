package de.themonstrouscavalca.formicacid.extractors.defn;

@FunctionalInterface
public interface IHandleValue<M,T>{
    void apply(M m, T t);
}
