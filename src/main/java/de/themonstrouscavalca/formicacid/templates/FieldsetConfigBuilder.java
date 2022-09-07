package de.themonstrouscavalca.formicacid.templates;

import java.util.*;

import de.themonstrouscavalca.formicacid.twirl.forms.attributes.html.attributesHtml;
import play.twirl.api.Html;

public class FieldsetConfigBuilder{
    String formName = "";
    String name = "";
    String legend = "";
    String id = "";

    List<String> fieldsetClasses;
    Map<String, String> fieldsetAttributes;

    public static FieldsetConfigBuilder instance(){
        return new FieldsetConfigBuilder();
    }

    public static FieldsetConfigBuilder of(String name, String legend){
        return of(name, legend, "");
    }

    public static FieldsetConfigBuilder of(String name, String legend, String formName){
        FieldsetConfigBuilder builder = new FieldsetConfigBuilder();
        builder.setName(name);
        builder.setLegend(legend);
        builder.setFormName(formName);
        builder.setId(FieldsetNamer.id(name, formName));
        return builder;
    }

    public FieldsetConfigBuilder(){
        this.fieldsetClasses = new ArrayList<>();
        this.fieldsetAttributes = new LinkedHashMap<>();
    }

    public FieldsetConfigBuilder setLegend(String legend){
        this.legend = legend;
        return this;
    }

    public FieldsetConfigBuilder setName(String name){
        this.name = name;
        return this;
    }

    public FieldsetConfigBuilder setId(String id){
        this.id = id;
        return this;
    }

    public FieldsetConfigBuilder setFormName(String formName){
        this.formName = formName;
        return this;
    }

    public FieldsetConfigBuilder addClass(String classString){
        this.fieldsetClasses.add(classString);
        return this;
    }

    public FieldsetConfigBuilder addAttribute(String key, String value){
        this.fieldsetAttributes.put(key, value);
        return this;
    }

    public FieldsetConfigBuilder addData(String dataKey, String value){
        return this.addAttribute(String.format("data-%s", dataKey), value);
    }

    String collateClasses(){
        return String.join(" ", this.fieldsetClasses);
    }


    public FieldsetConfig build(){
        return new FieldsetConfig(this);
    }
}
