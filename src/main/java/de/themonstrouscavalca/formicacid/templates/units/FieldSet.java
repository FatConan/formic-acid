package de.themonstrouscavalca.formicacid.templates.units;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.templates.FieldsetConfigBuilder;
import de.themonstrouscavalca.formicacid.templates.units.base.CollectingUnit;
import de.themonstrouscavalca.formicacid.templates.units.base.IAmAUnit;
import play.twirl.api.Html;
import play.twirl.api.HtmlFormat;
import scala.collection.immutable.Seq;
import scala.jdk.javaapi.CollectionConverters;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FieldSet extends CollectingUnit{
    public static Builder builder(){
        return new Builder();
    }

    private final FieldsetConfigBuilder config;

    public static class Builder{
        private FieldsetConfigBuilder config;
        private final Map<String, IAmAUnit> unitMap = new LinkedHashMap<>();

        public Builder config(FieldsetConfigBuilder config){
            this.config = config;
            return this;
        }

        public Builder unit(String name, IAmAUnit unit){
            this.unitMap.put(name, unit);
            return this;
        }

        public FieldSet build(){
            return new FieldSet(this);
        }
    }

    public FieldSet(Builder builder){
        super(builder.unitMap);
        this.config = builder.config;
    }

    @Override
    public Html render(){
        List<Html> renders = new ArrayList<>();
        for(IAmAUnit unit : unitMap.values()){
            renders.add(unit.render());
        }
        Seq<Html> htmls = CollectionConverters.asScala(renders).toSeq();
        return de.themonstrouscavalca.formicacid.twirl.forms.html.fieldset.render(this.config.build(), HtmlFormat.fill(htmls));
    }
}
