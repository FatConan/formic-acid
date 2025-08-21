package de.themonstrouscavalca.formicacid.marshallers.exceptions;

/**
 * A NotImplemented method that's most often used in the case where a marshaller either validates, or exports
 * but does not do both. This is often the case for data that has no directly editable view, but for which
 * a JSON representation is useful.
 */
public class NotImplemented extends RuntimeException{
    public NotImplemented(String message){
        super(message);
    }

    public NotImplemented(){
        super("The marshaller does not support this mechanism");
    }
}
