package de.themonstrouscavalca.formicacid.abs;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.defn.IHandleValue;
import de.themonstrouscavalca.formicacid.marshallers.defn.IMarshall;
import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.ValidatedOptional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * Building on top of the AbstractMarshaller this AbstractSimpleMarshaller requires subclasses to provide only
 * an export method and a set of individual field validations. By way of example, the tests for this project
 * include an implementation that specifies a subclass with the validation steps:
 *          @Override
 *          public void validationSteps(Book entity, JsonNode json){
 *             this.validateValue(entity, "id", json, this.longExtractor,
 *                     requiredOnly(),
 *                     (v, e) -> e.ID = v.get());
 *             this.validateValue(entity, "title", json, this.stringExtractor,
 *                     Arrays.asList(new RequiredValidator<>(), new StringLengthValidator(1, 255)),
 *                     (v, e) -> e.title = v.get());
 *             this.validateValue(entity, "author", json, this.stringExtractor,
 *                     Arrays.asList(new RequiredValidator<>(), new StringLengthValidator(1, 255)),
 *                     (v, e) -> e.author = v.get());
 *          }
 *
 * This potentially offers a more concise way of specifying a validator, rather than the more free-form
 * validate(JsonNode json) approach. While not as flexible as that, it does tend to be the better to use
 * for the majority of validation scenarios.
 *
 * @param <T> the type of the object returned by the marshaller
 */
public abstract class AbstractSimpleMarshaller<T> extends AbstractSimpleValidator<T> implements IMarshall<T>{

}
