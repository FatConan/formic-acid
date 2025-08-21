package de.themonstrouscavalca.formicacid.marshallers.defn;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * A fully fledged marshaller implements both IValidateForm and IExportToJson, so the IMarshall interface
 * just extends both of those as a shorthand. We also demand a way to get an error response from the marshaller,
 * which we don't ask of the raw validator.
 *
 * @param <T>
 */
public interface IMarshall<T> extends IValidateForm<T>, IExportToJson<T>{
    JsonNode getErrorResponse();
}
