package de.themonstrouscavalca.formicacid.helpers.dynamic;

import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.validators.defn.IValidate;

import java.util.Collection;

public class DynamicValidatorDefinition<T>{
    private final String fieldName;
    private final IExtract<T> extractor;
    private final Collection<IValidate<T>> validators;

    public DynamicValidatorDefinition(String fieldName, IExtract<T> extractor, Collection<IValidate<T>> validators){
        this.fieldName = fieldName;
        this.extractor = extractor;
        this.validators = validators;
    }

    public String getFieldName(){
        return fieldName;
    }

    public IExtract<T> getExtractor(){
        return extractor;
    }

    public Collection<IValidate<T>> getValidators(){
        return validators;
    }
}
