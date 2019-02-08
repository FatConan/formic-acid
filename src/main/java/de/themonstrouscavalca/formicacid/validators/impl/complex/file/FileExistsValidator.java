package de.themonstrouscavalca.formicacid.validators.impl.complex.file;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.io.File;
import java.util.Optional;

public class FileExistsValidator extends AbstractValidator<String> implements IValidate<String>{
    private final String DEFAULT_ERROR_MSG = "This file does not exist";

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    public FileExistsValidator(){
        super(null);
    }

    public FileExistsValidator(String errorMessage){
        super(errorMessage);
    }

    @Override
    public IntermediateValidateOptional<String> getValidatedValue(String value){
        IntermediateValidateOptional<String> intermediate = new IntermediateValidateOptional<>(value);
        if(this.isPresent(value)){
            File f = new File(value);
            if(!f.exists() || !f.canRead()){
                intermediate.setValid(false);
                intermediate.addError(this.getErrorMessage());
            }
        }
        return intermediate;
    }
}