package de.themonstrouscavalca.formicacid.validators.defn;

import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;

/**
 * IValidate performs a validation on an object ot type T and returns a sort of augmented optional that provides
 * additional information, such as any error messages associated with the validation process. IValidate is used
 * by IExtract to perform the extraction and validation of fields within a JSON representation.
 *
 * @param <T> the type of the validated object
 */
public interface IValidate<T>{
    IntermediateValidateOptional<T> getValidatedValue(T value);
}
