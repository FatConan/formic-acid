package de.themonstrouscavalca.formicacid.helpers;

import de.themonstrouscavalca.formicacid.helpers.responses.ResponsePackage;
import de.themonstrouscavalca.formicacid.helpers.responses.ResponseType;
import de.themonstrouscavalca.formicacid.marshallers.defn.IMarshall;

public class BasicMarshallingHelper <T extends IMarshall<V>, V> {
    public ResponsePackage appropriateResult(T marshaller, V entity){
        if(entity == null){
            return new ResponsePackage(ResponseType.ERROR, marshaller.getErrorResponse());
        }

        return new ResponsePackage(ResponseType.OK, marshaller.toJson(entity));
    }
}
