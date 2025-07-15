package de.themonstrouscavalca.formicacid.abs;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.defn.IHandleValue;
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
public abstract class AbstractSimpleMarshaller<T> extends AbstractMarshaller<T>{
    public abstract void validationSteps(T entity, JsonNode json);
    public abstract T create();

    protected <M> void validateValue(T entity, String fieldName, JsonNode json, IExtract<M> extractor, Collection<IValidate<M>> validators, IHandleValue<ValidatedOptional<M>, T> handleValue){
        ValidatedOptional<M> value = extractor.extractValidatedValue(fieldName, json, validators);
        this.addErrors(fieldName, value);
        if(value.isValid() && value.isPresent()){
            handleValue.apply(value, entity);
        }
    }

    protected <M> void validateValue(T entity, String fieldName, JsonNode json, IExtract<M> extractor, IHandleValue<ValidatedOptional<M>, T> handleValue){
        validateValue(entity, fieldName, json, extractor, Collections.emptyList(), handleValue);
    }

    @Override
    public Optional<T> validate(JsonNode json){
        if(json == null){
            jsonDecodeError();
            return Optional.empty();
        }

        T entity = this.create();
        this.validationSteps(entity, json);

        if(this.hasErrors()){
            return Optional.empty();
        }

        return Optional.of(entity);
    }
}
