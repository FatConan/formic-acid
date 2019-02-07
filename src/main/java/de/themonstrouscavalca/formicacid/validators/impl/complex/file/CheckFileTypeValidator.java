package de.themonstrouscavalca.formicacid.validators.impl.complex.file;

import de.themonstrouscavalca.formicacid.validators.defn.IValidate;
import de.themonstrouscavalca.formicacid.validators.helpers.IntermediateValidateOptional;
import de.themonstrouscavalca.formicacid.validators.impl.AbstractValidator;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

public class CheckFileTypeValidator extends AbstractValidator<String> implements IValidate<String>{
    private final String DEFAULT_ERROR_MSG = "This is not a valid type.";
    private final List<String> permissableTypes;

    @Override
    protected String defaultError(){
        return DEFAULT_ERROR_MSG;
    }

    public CheckFileTypeValidator(List<String> permissableTypes1){
        super(null);
        this.permissableTypes = permissableTypes1;
    }

    public CheckFileTypeValidator(String errorMessage, List<String> permissableTypes){
        super(errorMessage);
        this.permissableTypes = permissableTypes;
    }

    @Override
    public IntermediateValidateOptional<String> getValidatedValue(Optional<String> value){
        IntermediateValidateOptional<String> intermediate = new IntermediateValidateOptional<>(value);
        if(value.isPresent()){
            File f = new File(value.get());
            try{
                String fileType = Files.probeContentType(f.toPath());
                if(!permissableTypes.contains(fileType)){
                    intermediate.setValid(false);
                    intermediate.addError(this.getErrorMessage());
                }
            }catch(IOException e){
                intermediate.setValid(false);
                intermediate.addError(this.getErrorMessage());
            }

        }
        return intermediate;
    }
}

