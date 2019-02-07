package de.themonstrouscavalca.formicacid.validators.impl.complex.file;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.io.File;
import java.util.Optional;

public class PermittedLocationValidator extends AbstractValidator<String> implements IValidate<String>{
    private final String DEFAULT_ERROR_MSG = "This is not a permitted file location";
    private final String permittedBasePath;

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    public PermittedLocationValidator(String permittedBasePath){
        super(null);
        this.permittedBasePath = permittedBasePath;
    }

    public PermittedLocationValidator(String permittedBasePath, String errorMessage){
        super(errorMessage);
        this.permittedBasePath = permittedBasePath;
    }

    @Override
    public IntermediateValidateOptional<String> getValidatedValue(Optional<String> value){
        IntermediateValidateOptional<String> intermediate = new IntermediateValidateOptional<>(value);
        if(value.isPresent()){
            File f = new File(value.get());
            if(!f.getAbsolutePath().startsWith(this.permittedBasePath)){
                intermediate.setValid(false);
                intermediate.addError(this.getErrorMessage());
            }
        }
        return intermediate;
    }
}

