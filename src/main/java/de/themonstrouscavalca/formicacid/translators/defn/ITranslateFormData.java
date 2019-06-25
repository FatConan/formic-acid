package de.themonstrouscavalca.formicacid.translators.defn;

public interface ITranslateFormData<T, V>{
    V translate(T data);
}
