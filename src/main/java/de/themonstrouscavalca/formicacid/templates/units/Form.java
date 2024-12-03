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
    private static final String GLOBAL_ERRORS_KEY = "global_errors";
    private static final String ERRORS_KEY = "errors";

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
            if(errors.has(GLOBAL_ERRORS_KEY)){
                JsonNode globalErrs = errors.get(GLOBAL_ERRORS_KEY);
                StringBuilder globalErrsString = new StringBuilder();
                if(globalErrs.isArray()){
                    for(JsonNode e: globalErrs){
                        globalErrsString.append(e.asText());
                        globalErrsString.append("\n");
                    }
                }else if(globalErrs.isTextual()){
                    globalErrsString.append(globalErrs.asText());
                }
                this.errors(globalErrsString.toString());
            }
            if(errors.has(ERRORS_KEY)){
                super.handleErrors(errors.get(ERRORS_KEY));
            }
        }
    }

    @Override
    public void errors(String errors){
        this.config.errors(errors);
    }

    @Override
    public String name(){
        return this.config != null ? this.config.getName() : "";
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

        public Builder unit(IAmAUnit unit){
            this.unitMap.put(unit.name(), unit);
            return this;
        }

        public Form build(){
            return new Form(this);
        }
    }
}
