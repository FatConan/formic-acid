package de.themonstrouscavalca.formicacid.templates.units.base;

import java.util.List;

public interface IAcceptErrors{
    void errors(List<String> errors);
    default void error(String error){
        errors(List.of(error));
    }
}
