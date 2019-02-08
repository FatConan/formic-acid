package de.themonstrouscavalca.formicacid.extractors.impl.basic;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.extractors.defn.IExtract;
import de.themonstrouscavalca.formicacid.extractors.impl.AbstractExtractor;

import java.util.Optional;

public class DoubleExtractor extends AbstractExtractor<Double> implements IExtract<Double>{
    @Override
    public Double extractValueFromJson(JsonNode node){
        return this.missing(node) ? null : node.asDouble();
    }
}
