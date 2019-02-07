package de.themonstrouscavalca.formicacid.validators.defn;

import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;

import java.util.Optional;

public interface IValidate<T>{
    IntermediateValidateOptional<T> getValidatedValue(Optional<T> value);
}
