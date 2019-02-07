package de.themonstrouscavalca.formicacid.validators.impl;

import java.util.Optional;

public abstract class AbstractValidator<T> {
    private Optional<String> errorMsg = Optional.empty();
    protected abstract String defaultError();

    public AbstractValidator(){
        this(null);
    }

    public AbstractValidator(String errorMessage){
        this.errorMsg = Optional.ofNullable(errorMessage);
    }

    protected String getParameterisedErrorMessage(Object ... args){
        return String.format(this.getErrorMessage(), args);
    }

    protected String getErrorMessage(){
        return this.errorMsg.orElseGet(this::defaultError);
    }
}
