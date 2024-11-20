package de.themonstrouscavalca.formicacid.templates.units;

import com.fasterxml.jackson.databind.JsonNode;
import de.themonstrouscavalca.formicacid.templates.FormConfig;
import de.themonstrouscavalca.formicacid.templates.units.base.CollectingUnit;
import de.themonstrouscavalca.formicacid.templates.units.base.IAmAUnit;
import play.twirl.api.Html;
import play.twirl.api.HtmlFormat;
import scala.collection.immutable.Seq;
import scala.jdk.javaapi.CollectionConverters;

import java.util.*;

public class Form extends CollectingUnit{
    public static Builder builder(){
        return new Builder();
    }

    private final FormConfig config;

    public Form(Builder builder){
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
        return de.themonstrouscavalca.formicacid.twirl.forms.html.form.render(this.config,
                HtmlFormat.fill(htmls));
    }

    @Override
    public void handleErrors(JsonNode errors){
        if(errors != null){
            if(errors.has("global_errors")){
                this.errors(errors.get("global_errors").asText());
            }
            if(errors.has("errors")){
                super.handleErrors(errors.get("errors"));
            }
        }
    }

    @Override
    public void errors(String errors){
        this.config.errors(errors);
    }

    public static class Builder{
        private FormConfig config;
        private final Map<String, IAmAUnit> unitMap = new LinkedHashMap<>();

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
