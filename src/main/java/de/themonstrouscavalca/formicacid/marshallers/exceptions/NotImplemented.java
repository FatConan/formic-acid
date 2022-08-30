package de.themonstrouscavalca.formicacid.marshallers.exceptions;

public class NotImplemented extends RuntimeException{
    public NotImplemented(String message){
        super(message);
    }

    public NotImplemented(){
        super("The marshaller does not support this mechanism");
    }
}
