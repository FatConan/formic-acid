package de.themonstrouscavalca.formicacid.helpers;

import de.themonstrouscavalca.formicacid.abs.AbstractMarshaller;
import de.themonstrouscavalca.formicacid.helpers.responses.ResponsePackage;
import de.themonstrouscavalca.formicacid.helpers.responses.ResponseType;

public class BasicMarshallingHelper <T extends AbstractMarshaller<V>, V> {
    public ResponsePackage appropriateResult(T marshaller, V entity){
        if(entity == null){
            return new ResponsePackage(ResponseType.ERROR, marshaller.getErrorResponse());
        }

        return new ResponsePackage(ResponseType.OK, marshaller.toJson(entity));
    }
}
