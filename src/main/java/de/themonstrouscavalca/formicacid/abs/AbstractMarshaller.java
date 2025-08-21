package de.themonstrouscavalca.formicacid.abs;

import de.themonstrouscavalca.formicacid.marshallers.defn.IMarshall;

/** A marshaller is a class that provides methods to both validate a JSON representation of a particular object and
 * export a JSON representation of a particular object. This can be achieved by any class implementing
 * the IMarshall interface, but the AbstractMarshaller is designed as a more complex version that
 * supports a number of ancillary, but useful functions. These include providing a standardised error
 * response format (in JSON) that can be used with the HTML form representations and with the supporting
 * MalicAcid Javascript library, as well as methods to allow the use of sub-marshallers for complex objects and
 * corresponding forms, including methods to enable namespaced error handling.
 *
 * @param <T>
 */
public abstract class AbstractMarshaller<T> extends AbstractValidator<T> implements IMarshall<T>{

}
