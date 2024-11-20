package de.themonstrouscavalca.formicacid.templates.units;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.templates.FormConfig;
import de.themonstrouscavalca.formicacid.templates.units.base.IAmAUnit;
import play.twirl.api.Html;
import play.twirl.api.HtmlFormat;
import scala.collection.immutable.Seq;
import scala.jdk.javaapi.CollectionConverters;

import java.util.*;

public class Form implements IAmAUnit{
    public static Builder builder(){
        return new Builder();
    }

    private final Map<String, IAmAUnit> unitMap;
    private final FormConfig config;

    public Form(Builder builder){
        this.unitMap = builder.unitMap;
        this.config = builder.config;
    }

    @Override
    public Html render(){
        List<Html> renders = new ArrayList<>();
        for(IAmAUnit unit : unitMap.values()){
            renders.add(unit.render());
        }
        Seq<Html> htmls = CollectionConverters.asScala(renders).toSeq();
        return de.themonstrouscavalca.formicacid.twirl.forms.html.form.render(this.config,
                HtmlFormat.fill(htmls));
    }

    @Override
    public void errors(List<String> errors){
        this.config.addErrors(errors);
    }

    @Override
    public void data(String data){
        //no-op
    }

    @Override
    public void data(String[] data){
        //no-op
    }

    public void data(JsonNode data){
        for(Map.Entry<String, IAmAUnit> unitEntry : this.unitMap.entrySet()){
            String fieldName = unitEntry.getKey();
            IAmAUnit unit = unitEntry.getValue();
            if(data.has(fieldName)){
                unit.data(data.get(unitEntry.getKey()).asText());
            }else{
                unit.data((String) null);
            }
        }
    }

    public void data(Map<String, String[]> data){
        for(Map.Entry<String, IAmAUnit> unitEntry : this.unitMap.entrySet()){
            String fieldName = unitEntry.getKey();
            IAmAUnit unit = unitEntry.getValue();
            if(data.containsKey(fieldName)){
                unit.data(data.get(fieldName));
            }else{
                unit.data((String) null);
            }
        }
    }

    public static class Builder{
        private FormConfig config;
        private final Map<String, IAmAUnit> unitMap = new TreeMap<>();

        public Builder config(FormConfig config){
            this.config = config;
            return this;
        }

        public Builder unit(String name, IAmAUnit unit){
            this.unitMap.put(name, unit);
            return this;
        }

        public Form build(){
            return new Form(this);
        }
    }
}
